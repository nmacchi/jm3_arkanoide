/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;
import java.util.Arrays;
import java.util.List;
import mygame.classes.Brick;

/**
 *
 * @author nicolas
 */
public class MainReloaded extends SimpleApplication {

    public static void main(String[] args) {
        MainReloaded app = new MainReloaded();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        setCamPosition();

       


        Box box = new Box(2.49f, 3.5f, 0.1f);
        Geometry floor = new Geometry("Floor", box);
        floor.setLocalTranslation(0.00f, 0.00f, 0.00f);
        Material floorMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture floorText = assetManager.loadTexture("Textures/floor2.jpg");
        floorMaterial.setTexture("ColorMap", floorText);
        floor.setMaterial(floorMaterial);
        rootNode.attachChild(floor);

        Box goldenBox1 = new Box(0.15f, 3.5f, 0.1f);
        Geometry goldenBox1_geo = new Geometry("Left_golden_brick", goldenBox1);
        goldenBox1_geo.setLocalTranslation(-2.35f, 0.0f, 0.20f);
        Material goldenBox1_Material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture goldenBox1_Text = assetManager.loadTexture("Textures/golden.jpg");
        goldenBox1_Material.setTexture("ColorMap", goldenBox1_Text);
        goldenBox1_geo.setMaterial(goldenBox1_Material);
        rootNode.attachChild(goldenBox1_geo);

        //Box goldenBox2 = new Box(0.15f, 3.5f, 0.1f);
        Geometry goldenBox2_geo = new Geometry("Right_golden_brick", goldenBox1);
        goldenBox2_geo.setLocalTranslation(2.35f, 0.0f, 0.20f);
        Material goldenBox2_Material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture goldenBox2_Text = assetManager.loadTexture("Textures/golden.jpg");
        goldenBox2_Material.setTexture("ColorMap", goldenBox2_Text);
        goldenBox2_geo.setMaterial(goldenBox2_Material);
        rootNode.attachChild(goldenBox2_geo);

        Box goldenBox3 = new Box(2.2f, 0.15f, 0.1f);
        Geometry goldenBox3_geo = new Geometry("Top_golden_brick", goldenBox3);
        goldenBox3_geo.setLocalTranslation(0.0f, 3.35f, 0.20f);
        Material goldenBox3_Material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture goldenBox3_Text = assetManager.loadTexture("Textures/golden.jpg");
        goldenBox3_Material.setTexture("ColorMap", goldenBox3_Text);
        goldenBox3_geo.setMaterial(goldenBox3_Material);
        rootNode.attachChild(goldenBox3_geo);


        float initPositionX = -1.80f;
        float nextPositionY = 0.0f;

        //SUMO 0.36
        int numLines = 1;
        int bricksPerLine = 5;

        for (int y = 0; y <= numLines; y++) {
            float nextPositionX = initPositionX;
            for (int i = 0; i <= bricksPerLine; i++) {
                Box brickBox = new Box(0.36f, 0.15f, 0.1f);
                Geometry brick = new Geometry("brick_"+y+i, brickBox);
                
                brick.setLocalTranslation(nextPositionX, nextPositionY, 0.20f);
                
                Material brickMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                brickMaterial.setColor("Color", ColorRGBA.Blue);
                brick.setMaterial(brickMaterial);
                
                rootNode.attachChild(brick);
                
                System.out.println(i + " " + "Posicion: " + nextPositionX);
                
                nextPositionX +=  0.36f *2;
                
                
                
                if(i == 1)
                    break;
            }
            break;
        }







    }

    private void setCamPosition() {
        // flyCam.setEnabled(false);
//        cam.setLocation(new Vector3f(0,4f,4f));
//        cam.lookAt(new Vector3f(0.0f, -2.3f, -2f), new Vector3f(0,0,0));
    }
}
