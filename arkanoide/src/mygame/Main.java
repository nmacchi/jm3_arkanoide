package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.Trigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

import com.jme3.texture.Texture;
import java.util.Arrays;
import java.util.List;
import mygame.classes.Arkanoide;
import mygame.classes.Brick;
import mygame.classes.Floor;

/**
 * test
 *
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    //3D objects
    private Arkanoide arkanoide;
    private List<Brick> brickList;
    //Nodes
    
    Node pivot = new Node("pivot");
    Node nodeArkanoide = new Node("arkanoide");
    Node nodeBall = new Node("ball");
    Node nodeBrick = new Node("brick");
    //Key Mappings
    private static final String MAPPING_LEFT = "left";
    private static final String MAPPING_RIGHT = "right";
    
    private static final float speed = 2.0f;
    
    //private static float brickPositionY = -2.3f;
    //private static float brickScaleY = 0.15f;
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        initKeys();
        setCamPosition();
        setSceneLights();
        
        rootNode.attachChild(pivot);

        arkanoide = new Arkanoide(assetManager);
        nodeArkanoide.attachChild(arkanoide.getSpatial());
        
        pivot.attachChild(nodeArkanoide);

        Spatial ball = assetManager.loadModel("Models/Ball/Ball.mesh.xml");
        ball.setLocalTranslation(0.0f, -2.3f, 1f);
        ball.setLocalScale(0.07f);
        nodeBall.attachChild(ball);
        
        pivot.attachChild(nodeBall);
        
        Box box = new Box(2.49f, 0.1f, 3.5f);
        Geometry floor = new Geometry("Floor", box);

        floor.setLocalTranslation(-0.01f, -2.5f, -2.3f);
        Material floorMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture floorText = assetManager.loadTexture("Textures/floor2.jpg");

        floorMaterial.setTexture("ColorMap", floorText);
        floor.setMaterial(floorMaterial);

        rootNode.attachChild(floor);

        new Floor(assetManager,new Vector3f(2.49f, 0.1f, 3.5f));

        Box goldenBox1 = new Box(0.15f, 0.1f, 3.5f);
        Geometry goldenBox1_geo = new Geometry("Left_golden_brick", goldenBox1);

        goldenBox1_geo.setLocalTranslation(-2.35f, -2.3f, -2.3f);
        Material goldenBox1_Material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture goldenBox1_Text = assetManager.loadTexture("Textures/golden.jpg");

        goldenBox1_Material.setTexture("ColorMap", goldenBox1_Text);
        goldenBox1_geo.setMaterial(goldenBox1_Material);

        rootNode.attachChild(goldenBox1_geo);
        //Box goldenBox2 = new Box(0.15f, 0.1f, 3.5f);
        Geometry goldenBox2_geo = new Geometry("Right_golden_brick", goldenBox1);

        goldenBox2_geo.setLocalTranslation(2.32f, -2.3f, -2.3f);
        Material goldenBox2_Material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture goldenBox2_Text = assetManager.loadTexture("Textures/golden.jpg");

        goldenBox2_Material.setTexture("ColorMap", goldenBox2_Text);
        goldenBox2_geo.setMaterial(goldenBox2_Material);

        rootNode.attachChild(goldenBox2_geo);
        Box goldenBox2 = new Box(2.2f, 0.1f, 0.15f);
        Geometry goldenBox3_geo = new Geometry("Top_golden_brick", goldenBox2);

        goldenBox3_geo.setLocalTranslation(0.0f, -2.3f, -5.65f);
        Material goldenBox3_Material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture goldenBox3_Text = assetManager.loadTexture("Textures/golden.jpg");

        goldenBox3_Material.setTexture("ColorMap", goldenBox3_Text);
        goldenBox3_geo.setMaterial(goldenBox3_Material);

        rootNode.attachChild(goldenBox3_geo);
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
            Vector3f v = pivot.getLocalTranslation();
            if (name.equals(MAPPING_LEFT)) {
                pivot.setLocalTranslation(v.x - value*speed, v.y, v.z);
            }
            if (name.equals(MAPPING_RIGHT)) {
                pivot.setLocalTranslation(v.x + value*speed, v.y, v.z);
            }
        }
    };
}
