package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.input.KeyInput;
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

import com.jme3.texture.Texture;
import java.util.Arrays;
import java.util.List;
import mygame.classes.Arkanoide;
import mygame.classes.Ball;
import mygame.classes.Brick;
import mygame.classes.GameField;

/**
 * test
 *
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    //3D objects
    private Arkanoide arkanoide;
    private Ball ball;
    private List<Brick> brickList;
    private GameField gameField;
    
    //Nodes 
    Node pivot = new Node("pivot");
    Node nodeArkanoide = new Node("arkanoide");
    //Node nodeBall = new Node("ball");
    Node nodeBrick = new Node("brick");
    
    //Key Mappings
    private static final String MAPPING_LEFT = "left";
    private static final String MAPPING_RIGHT = "right";
    
    //Physics app state
    private BulletAppState bulletAppState;
    
    private static final float speed = 2.0f;
    
    //private static float brickPositionY = -2.3f;
    //private static float brickScaleY = 0.15f;
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        //init physics
//        bulletAppState = new BulletAppState();
//        stateManager.attach(bulletAppState);
        
        initKeys();
        setCamPosition();
        setSceneLights();

        
        ball = new Ball(assetManager);
        //pivot.attachChild(ball.getGeometry());
        rootNode.attachChild(ball);
        
        arkanoide = new Arkanoide(assetManager);
        nodeArkanoide.attachChild(arkanoide.getSpatial());
        nodeArkanoide.attachChild(ball);
        
        rootNode.attachChild(nodeArkanoide);
        //nodeArkanoide.attachChild(arkanoide.getSpatial());        
        //pivot.attachChild(nodeArkanoide);
        
        //rootNode.attachChild(pivot);
        
        gameField = new GameField(assetManager);
        rootNode.attachChild(gameField);
        

        float initPositionX = -1.84f;
        float nextPositionZ = -2.8f;
        //SUMO 0.36
        int numLines = 6;
        int bricksPerLine = 5;
        List<ColorRGBA> bricksColors = Arrays.asList(ColorRGBA.Blue, ColorRGBA.Red, ColorRGBA.Green, ColorRGBA.Yellow, ColorRGBA.Magenta, ColorRGBA.Cyan, ColorRGBA.Gray);
        for (int y = 0;
                y <= numLines;
                y++) {
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
        //TODO: add update code
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
    
    /**
     * Lights
     */
    private void setSceneLights(){
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
        inputManager.addMapping(MAPPING_LEFT, new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addMapping(MAPPING_RIGHT, new KeyTrigger(KeyInput.KEY_RIGHT));

        inputManager.addListener(analogListener, MAPPING_LEFT, MAPPING_RIGHT);
    }
    /**
     * Listeners
     */
    private AnalogListener analogListener = new AnalogListener() {
        public void onAnalog(String name, float value, float tpf) {
            //Vector3f v = arkanoide.getSpatial().getLocalTranslation();
            Vector3f v = nodeArkanoide.getLocalTranslation();
            if (name.equals(MAPPING_LEFT)) {
                nodeArkanoide.setLocalTranslation(v.x - value*speed, v.y, v.z);
            }
            if (name.equals(MAPPING_RIGHT)) {
                nodeArkanoide.setLocalTranslation(v.x + value*speed, v.y, v.z);
            }
        }
    };
    
    
}
