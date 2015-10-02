/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.classes;

import com.jme3.asset.AssetManager;
import com.jme3.bounding.BoundingSphere;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.collision.CollisionResults;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
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
    
    private String collideWith;
    
    private Ray ray = new Ray();
    private CollisionResults collisions = new CollisionResults();
    private Vector3f collisionContactNormal;
    
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
    
    public Boolean hasCollision(Node rootNode){
         //Ray ray = new Ray(ball.getLocalTranslation(), new Vector3f(ball.getLocalTranslation().getX() + 1, ball.getLocalTranslation().getY() , ball.getLocalTranslation().getZ() - 5.65f));
        //Ray ray = new Ray(this.getLocalTranslation(),new Vector3f(this.getDirection().x , 0.0f , this.getDirection().z));
        ray.setOrigin(this.getLocalTranslation());
        ray.setDirection(new Vector3f(this.getDirection().x , 0.0f , this.getDirection().z));
        
        
//        
        rootNode.collideWith(ray, collisions);
//
        if(collisions.size() > 0){
            for(int i = 0; i < collisions.size(); i++){
                //System.out.println(collisions.getCollision(i).getGeometry().getName() + " - ");
//                
                if(!collisions.getCollision(i).getGeometry().getName().equals("ballMesh")){      
                    if(this.collideWith(rootNode.getChild(collisions.getCollision(i).getGeometry().getName()).getWorldBound(), collisions) > 0){
                        collisionContactNormal = collisions.getCollision(i).getContactNormal();
                        return true;
                    }   
                    //this.setBallReleased(Boolean.TRUE);
                    //return collisions.getCollision(i).getContactPoint();
                }           
            }
        }
        
        return false;
    }

    public Vector3f getCollisionContactNormal() {
        return collisionContactNormal;
    }

    public void setCollisionContactNormal(Vector3f collisionContactNormal) {
        this.collisionContactNormal = collisionContactNormal;
    }

    
    
}
