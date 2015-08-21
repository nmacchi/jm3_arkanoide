package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;

import java.util.Arrays;
import java.util.List;
import mygame.classes.Arkanoide;
import mygame.classes.Ball;
import mygame.classes.Brick;
import mygame.classes.GameField;
import mygame.classes.keyMappings.KeyMappings;

/**
 * test
 *
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    //3D objects
    private Ball ball;
    private Arkanoide arkanoide;
    private List<Brick> brickList;
    private GameField gameField;
    private Geometry mark;
    
    //Nodes 
    Node nodeArkanoide = new Node("arkanoide");
    Node nodeBrick = new Node("brick");
    Vector3f intersection = new Vector3f();

    public static void main(String[] args) {
        Main app = new Main();
        
        app.start();
    }

    @Override
    public void simpleInitApp() {
        initKeys();
        setCamPosition();
        setSceneLights();
        initMark();

        ball = new Ball(assetManager);
        rootNode.attachChild(ball);

        arkanoide = new Arkanoide(assetManager);
        nodeArkanoide.attachChild(arkanoide.getSpatial());

        rootNode.attachChild(nodeArkanoide);

        gameField = new GameField(assetManager);
        rootNode.attachChild(gameField);

        float initPositionX = -1.84f;
        float nextPositionZ = -2.8f;
        //SUMO 0.36
        int numLines = 6;
        int bricksPerLine = 5;
        List<ColorRGBA> bricksColors = Arrays.asList(ColorRGBA.Blue, ColorRGBA.Red, ColorRGBA.Green, ColorRGBA.Yellow, ColorRGBA.Magenta, ColorRGBA.Cyan, ColorRGBA.Gray);
        for (int y = 0; y <= numLines; y++) {
            float nextPositionX = initPositionX;
            for (int i = 0; i <= bricksPerLine; i++) {

                //Brick brick = new Brick(assetManager, nextPositionX, nextPositionZ);
                Box brick = new Box(0.36f, 0.15f, 0.1f);
                Geometry brickGeometry = new Geometry();
                brickGeometry.setMesh(brick);
                brickGeometry.setName("brick_" + y + i);
                brickGeometry.setLocalTranslation(nextPositionX, -2.3f, nextPositionZ);

                Material brickMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                brickMaterial.setColor("Color", bricksColors.get(y));
                brickGeometry.setMaterial(brickMaterial);

                nodeBrick.attachChild(brickGeometry);

                nextPositionX += (0.36f * 2) + 0.01f;

                if (i == bricksPerLine) {
                    nextPositionZ -= (0.1f * 2) + 0.03f;
                }
            }

        }

        rootNode.attachChild(nodeBrick);

    }

    @Override
    public void simpleUpdate(float tpf) {

        
        
        
        
        if(arkanoide.isBallReleased()){
            //if(ball.getGeometry().getWorldTranslation() != intersection){
               System.out.println(ball.getDirection());
               ball.move(ball.getDirection().mult(0.001f));
            //}
        }
        
        //TODO: add update code
        //ball.move(new Vector3f(ball.getLocalTranslation().getX() + 1, ball.getLocalTranslation().getY(), ball.getLocalTranslation().getZ() + (-3f)).mult(0.01f));
        //ball.getChild("ballMesh").collideWith(((Geometry)ball.getChild("ballMesh")).getModelBound(), collisionResults);
        //System.out.println(ball.getLocalTranslation());
        //if(collisionResults.size() > 0){
        //    System.out.println(collisionResults.getClosestCollision().getGeometry().getName());
        //}
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    private void setCamPosition() {
        flyCam.setEnabled(false);
        cam.setLocation(new Vector3f(0, 4f, 4f));
        cam.lookAt(new Vector3f(0.0f, -2.3f, -2f), new Vector3f(0, 0, 0));
    }

    private void initMark() {
        Sphere sphere = new Sphere(32, 32, 0.05f, true, false);
        mark = new Geometry("mark", sphere);

        Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", ColorRGBA.Red);

        mark.setMaterial(material);
    }

    /**
     * Lights
     */
    private void setSceneLights() {
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -2f, -10.0f).normalizeLocal());
        rootNode.addLight(sun);

        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(1.3f));
        rootNode.addLight(al);
    }

    /**
     * Register keys
     */
    private void initKeys() {
        inputManager.addMapping(KeyMappings.MAPPING_LEFT, new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addMapping(KeyMappings.MAPPING_RIGHT, new KeyTrigger(KeyInput.KEY_RIGHT));
        inputManager.addMapping(KeyMappings.MAPPING_SHOOT, new KeyTrigger(KeyInput.KEY_SPACE));

        inputManager.addListener(analogListener, KeyMappings.MAPPING_LEFT, KeyMappings.MAPPING_RIGHT);
        inputManager.addListener(actionListener, KeyMappings.MAPPING_SHOOT);
    }
    /**
     * Listeners
     */
    private AnalogListener analogListener = new AnalogListener() {
        public void onAnalog(String name, float value, float tpf) {
            //Vector3f v = arkanoide.getSpatial().getLocalTranslation();
            //Vector3f v = nodeArkanoide.getLocalTranslation();

            if (name.equals(KeyMappings.MAPPING_LEFT)) {
                nodeArkanoide.setLocalTranslation(nodeArkanoide.getLocalTranslation().getX() - value * speed, nodeArkanoide.getLocalTranslation().getY(), nodeArkanoide.getLocalTranslation().getZ());
                
                if(!arkanoide.isBallReleased())
                    ball.setLocalTranslation(ball.getLocalTranslation().getX() - value * speed, ball.getLocalTranslation().getY(), ball.getLocalTranslation().getZ());
            }
            if (name.equals(KeyMappings.MAPPING_RIGHT)) {
                nodeArkanoide.setLocalTranslation(nodeArkanoide.getLocalTranslation().getX() + value * speed, nodeArkanoide.getLocalTranslation().getY(), nodeArkanoide.getLocalTranslation().getZ());
                
                if(!arkanoide.isBallReleased())
                    ball.setLocalTranslation(ball.getLocalTranslation().getX() + value * speed, ball.getLocalTranslation().getY(), ball.getLocalTranslation().getZ());
            }
        }
    };
    private ActionListener actionListener = new ActionListener() {
        public void onAction(String name, boolean isPressed, float tpf) {
            if (name.equals(KeyMappings.MAPPING_SHOOT) && !isPressed) {

                Vector3f intersection = arkanoide.shootBall(ball, nodeArkanoide);
                //System.out.println(intersection);

                mark.setLocalTranslation(intersection);
                rootNode.attachChild(mark);

                //bulletAppState.getPhysicsSpace().add(ball);
            }
        }
    };
}
