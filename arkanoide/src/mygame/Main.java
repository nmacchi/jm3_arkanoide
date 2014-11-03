package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;

import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

import com.jme3.texture.Texture;
import mygame.classes.Arkanoide;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {
    
    private Arkanoide arkanoide;
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        arkanoide = new Arkanoide(assetManager);
        rootNode.attachChild(arkanoide.getSpatial());
        
        setCamPosition();
        
        Spatial ball = assetManager.loadModel("Models/Ball/Ball.mesh.xml");
        ball.setLocalTranslation(0.0f, -2.3f, 1f);
        ball.setLocalScale(0.07f);
        rootNode.attachChild(ball);
        

        
        
        Box box = new Box(2.49f, 0.1f, 3.5f);
        Geometry floor = new Geometry("Floor", box);
        floor.setLocalTranslation(-0.01f, -2.5f, -2.3f);
        Material floorMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture floorText = assetManager.loadTexture("Textures/floor2.jpg");
        floorMaterial.setTexture("ColorMap",floorText);
        floor.setMaterial(floorMaterial);
        rootNode.attachChild(floor);
        
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
        
        
        float initPositionX= -1.84f;
        float nextPositionZ= -2.8f;
        float brickWidth = 0.36f;
        float brickHeight = 0.1f;
        //SUMO 0.36
        for(int y=0; y <=6; y++){
            float nextPositionX = initPositionX;
            for(int i=0; i<=5; i++){
                Spatial spatial = assetManager.loadModel("Models/Brick/Brick.mesh.xml");
                spatial.setName("brick_"+y+i);
                spatial.setLocalScale(brickWidth, 0.15f, brickHeight);
            
                spatial.setLocalTranslation(nextPositionX, -2.3f, nextPositionZ);
            
                nextPositionX +=  (brickWidth * 2) + 0.01f;
            
                rootNode.attachChild(spatial);
            }
            nextPositionZ -= (brickHeight * 2) + 0.01f;   
        }
                
        
        
        
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -2f, -10.0f).normalizeLocal());
        rootNode.addLight(sun);
        
        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(1.3f));
        rootNode.addLight(al);
        
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
    
    private void setCamPosition(){
        flyCam.setEnabled(false);
        cam.setLocation(new Vector3f(0,4f,4f));
        cam.lookAt(new Vector3f(0.0f, -2.3f, -2f), new Vector3f(0,0,0));
    }
}
