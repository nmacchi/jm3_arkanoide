/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.classes;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;

/**
 *
 * @author nicolas
 */
public class Floor {
    Float positionX = -0.01f;
    Float positionY = -2.5f;
    Float positionZ = -2.3f;
    
    Box box;
    String name = "Floor";
    
    Material floorMat;
    Texture floorTex;
    
    
    public Floor(AssetManager assetManager, Vector3f dimensions){
        box = new Box(dimensions.x, dimensions.y, dimensions.z);
        
        
    }
    
}
