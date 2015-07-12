/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.classes;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;

/**
 *
 * @author nicolas
 */
public class Floor {
    Box box;
    
    /*Dimensions*/
    private static Float WIDTH = 2.49f;
    private static Float HEIGHT = 0.1f;
    private static Float LENGTH = 3.5f;
    
    /*Position*/
    private static final Vector3f position = new Vector3f(-0.01f,-2.5f,-2.3f);
        
    private Geometry geometry;
    private Texture texture;
    private Material material;
    
    
    public Floor(AssetManager assetManager){
        box = new Box(WIDTH, HEIGHT, LENGTH);
        geometry = new Geometry("Floor", box);
        material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        
        texture = assetManager.loadTexture("Textures/floor2.jpg");
        material.setTexture("ColorMap", texture);
        geometry.setMaterial(material);
        geometry.setLocalTranslation(position);
        
        
    }

    public Vector3f getPosition() {
        return position;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
    
    
}
