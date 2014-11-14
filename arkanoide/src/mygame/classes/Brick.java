/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.classes;

import com.jme3.asset.AssetManager;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

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
    
    
    private Spatial brick;
    private String name;
    
    
    private Vector3f position; 
    private Vector3f scale;
    
    public Brick(AssetManager assetManager, float positionX, float positionZ){
        brick = assetManager.loadModel(model);
        
        this.position = new Vector3f(positionX, brickPositionY, positionZ);
        this.scale = new Vector3f(brickWidth,brickScaleY,brickHeight);
        
        brick.setLocalScale(scale);
        brick.setLocalTranslation(position);
    }

    public Spatial getBrick() {
        return brick;
    }

    public void setBrick(Spatial brick) {
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
