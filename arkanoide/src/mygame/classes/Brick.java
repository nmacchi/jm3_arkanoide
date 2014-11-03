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
    private static final float brickWidth = 0.36f;
    private static final float brickHeight = 0.1f;
    private Spatial brick;
    
    private Vector3f position; 
    private Vector3f scale;
    
    Brick(AssetManager assetManager){
        brick = assetManager.loadModel(model);
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
    
    
    
}
