package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;

import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Line;
import com.jme3.scene.shape.Sphere;

import java.util.Arrays;
import java.util.List;
import mygame.classes.Arkanoide;
import mygame.classes.Ball;
import mygame.classes.Brick;
import mygame.classes.GameField;
import mygame.classes.keyMappings.KeyMappings;
import sun.awt.image.PixelConverter;

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
    private Ray ray = new Ray();
    
    //Used by ball rotation
    //private Quaternion q = new Quaternion();
    
    CollisionResults collisions = new CollisionResults();
    //Nodes 
    Node nodeArkanoide = new Node("arkanoide");
    Node nodeBrick = new Node("nodeBricks");
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





        if (arkanoide.isBallReleased()) {
            //if(ball.getGeometry().getWorldTranslation() != intersection){
            //System.out.println(ball.getDirection());
            ball.move(ball.getDirection().mult(tpf/ball.getSpeed()));
            //}

            //System.out.println(ball.getGeometryNameToCollision(rootNode));

            if (ball.hasCollision(rootNode)) {
                //ball.evaluateNewDirection();
                
                Geometry g = (Geometry)rootNode.getChild(ball.getCollideWith());
                if(((Node)rootNode.getChild("nodeBricks")).hasChild(g)){
                    //If geometry belongs to "nodeBrick" removes from root node
                    System.out.println(g.getName());
                    g.removeFromParent();
                    rootNode.updateGeometricState();
                }
                
                
              
                
//                Vector3f normal = ball.getContactPointNormal();
//                Vector3f direction = ball.getDirection().negate();
//                Vector3f axis = direction.cross(normal).normalizeLocal();
//              
                
                //DEBUG
//                Line l1 = new Line(ball.getContactPoint(), normal);
//                Line l2 = new Line(ball.getContactPoint(), direction);
//                Line l3 = new Line(ball.getContactPoint(), axis);
//                
//                
//                
//                Geometry g1 = new Geometry("normal", l1);
//                Material m1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//                m1.setColor("Color", ColorRGBA.White);
//                g1.setMaterial(m1);
//                rootNode.attachChild(g1);
                
//                Geometry g2 = new Geometry("direction", l2);
//                Material m2 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//                m2.setColor("Color", ColorRGBA.Red);
//                g2.setMaterial(m2);
//                rootNode.attachChild(g2);
                
//                Geometry g3 = new Geometry("axis", l3);
//                Material m3 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//                m3.setColor("Color", ColorRGBA.Green);
//                g3.setMaterial(m3);
//                rootNode.attachChild(g3);
                
                
                //
                
//                float cosAlpha = direction.dot(normal);
//                float alpha = FastMath.acos(cosAlpha);
////               
//                Quaternion q = new Quaternion().fromAngleAxis(alpha, axis);
                //Vector3f reflection = q.multLocal(normal);
                
                
//                ball.setDirection(q.multLocal(normal));

            }


        }

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
        Sphere sphere = new Sphere(16, 16, 0.05f);
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

                if (!arkanoide.isBallReleased()) {
                    ball.setLocalTranslation(ball.getLocalTranslation().getX() - value * speed, ball.getLocalTranslation().getY(), ball.getLocalTranslation().getZ());
                }
            }
            if (name.equals(KeyMappings.MAPPING_RIGHT)) {
                nodeArkanoide.setLocalTranslation(nodeArkanoide.getLocalTranslation().getX() + value * speed, nodeArkanoide.getLocalTranslation().getY(), nodeArkanoide.getLocalTranslation().getZ());

                if (!arkanoide.isBallReleased()) {
                    ball.setLocalTranslation(ball.getLocalTranslation().getX() + value * speed, ball.getLocalTranslation().getY(), ball.getLocalTranslation().getZ());
                }
            }
        }
    };
    private ActionListener actionListener = new ActionListener() {
        public void onAction(String name, boolean isPressed, float tpf) {
            if (name.equals(KeyMappings.MAPPING_SHOOT) && !isPressed && !arkanoide.isBallReleased()) {
                
//                Vector3f extent = ((BoundingBox) gameField.getChild("Floor").getWorldBound()).getExtent(new Vector3f());
//                
//                System.out.println("Alto: " + extent.z);
                
                arkanoide.shootBall(ball, nodeArkanoide);
                //System.out.println(intersection);

                //mark.setLocalTranslation(intersection);
                //rootNode.attachChild(mark);

                //bulletAppState.getPhysicsSpace().add(ball);
            }
        }
    };
}
