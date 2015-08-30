/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.classes;

import com.jme3.asset.AssetManager;
import com.jme3.bounding.BoundingSphere;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;

/**
 *
 * @author nicolas
 */
public class Ball extends Geometry{
    
    private static final Vector3f INITIAL_POSITION = new Vector3f(0.0f, -2.3f, 1f); 
    private static final float BALL_SCALE = 0.07f; //Radius
    private static String TEXTURE = "Textures/metal_texture_sphere.jpg";
    
    private Geometry geometry;
    private Vector3f direction;
    
    public Ball(AssetManager assetManager){
        super("ballMesh", new Sphere(16,16,BALL_SCALE,true,false));
        
        //sphere.setTextureMode(Sphere.TextureMode.Projected);

        this.setLocalTranslation(INITIAL_POSITION);
        
        this.setMaterial(createMaterial(assetManager));
        
//        geometry.setModelBound(new BoundingSphere());
//        geometry.updateModelBound();
      
        //this.attachChild(geometry);
        
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Vector3f getDirection() {
        return direction;
    }

    public void setDirection(Vector3f direction) {
        this.direction = direction;
    }
   
    private Material createMaterial(AssetManager assetManager){
        material = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        //material.setTexture("DiffuseMap", assetManager.loadTexture(TEXTURE));
        //material.setBoolean("UseMaterialColors",true);
        material.setColor("Diffuse", ColorRGBA.White);
        //material.setColor("Specular", ColorRGBA.White);
        //material.setFloat("Shininess", 64f);
       
        return material;
    }
}
