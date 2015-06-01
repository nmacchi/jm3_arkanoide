/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.classes;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 *
 * @author nicolas
 */
public class Brick {
    
    private static final String model = "Models/Brick/Brick.mesh.xml";
    
    private static float brickWidth = 0.36f;
    private static float brickHeight = 0.1f;
    private static float brickScaleY = 0.15f;
    
    private static float brickPositionY = -2.3f;
    
    
    private Geometry brick;
    private String name;
    
    
    private Vector3f position; 
    private Vector3f scale;
    
    public Brick(AssetManager assetManager, float positionX, float positionZ){
        //brick = assetManager.loadModel(model); 
        Box box = new Box();
        brick = new Geometry();
        brick.setMesh(box);
        
        Material brickMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        brickMaterial.setColor("Color", ColorRGBA.Blue);
        brick.setMaterial(brickMaterial);
        
        this.position = new Vector3f(positionX, brickPositionY, positionZ);
        this.scale = new Vector3f(brickWidth,brickScaleY,brickHeight);
        
        brick.setLocalScale(scale);
        brick.setLocalTranslation(position);
    }

    public Geometry getBrick() {
        return brick;
    }

    public void setBrick(Geometry brick) {
        this.brick = brick;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public float getWidth(){
        return brickWidth;
    }
    
    public float getHeight(){
        return brickHeight;
    }
}
