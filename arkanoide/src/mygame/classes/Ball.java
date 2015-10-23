/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.classes;

import com.jme3.asset.AssetManager;
import com.jme3.collision.CollisionResults;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
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
    
    private float speed = 0.50f;
    private static final Vector3f INITIAL_POSITION = new Vector3f(0.0f, -2.3f, 1f); 
    private static final float BALL_SCALE = 0.07f; //Radius
    private static String TEXTURE = "Textures/metal_texture_sphere.jpg";
    
    private Geometry geometry;
    private Vector3f direction;
    private String collideWith;
    
    private Ray ray = new Ray();
    private CollisionResults collisions = new CollisionResults();
    private Vector3f contactPointNormal;
    
    private Quaternion q = new Quaternion();
    
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
        ray.setOrigin(this.getLocalTranslation());
        ray.setDirection(this.getDirection());
        
        collisions.clear();
        
        rootNode.collideWith(ray, collisions);

        if(collisions.size() > 0){
            for(int i = 0; i < collisions.size(); i++){
                if(!collisions.getCollision(i).getGeometry().getName().equals("ballMesh")){      
                    if(this.collideWith(rootNode.getChild(collisions.getCollision(i).getGeometry().getName()).getWorldBound(), collisions) > 0){
                        collideWith = collisions.getCollisionDirect(i+1).getGeometry().getName();
                        //this.setContactPointNormal(collisions.getCollisionDirect(i+1).getContactNormal().setY(0.0f));
                        Vector3f normal = collisions.getCollisionDirect(i+1).getContactNormal().normalizeLocal();
                        
                        this.evaluateNewDirection(normal);
                        
                        return true;
                    }   
                }           
            }
        }
        
        return false;
    }

    public Vector3f getContactPointNormal() {
        return contactPointNormal;
    }
    
    public void evaluateNewDirection(Vector3f normalVector){
  
        Vector3f directionVector = this.getDirection();
        Vector3f axis = directionVector.negate().cross(normalVector);
        
        float cosAlpha = directionVector.negate().dot(normalVector);
        float alpha = FastMath.acos(cosAlpha);               
        
        this.setDirection(q.fromAngleAxis(alpha, axis).mult(normalVector).setY(0.0f));
    }
    
    public void setContactPointNormal(Vector3f contactPointNormal) {
        this.contactPointNormal = contactPointNormal;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public String getCollideWith() {
        return collideWith;
    }

    public void setCollideWith(String collideWith) {
        this.collideWith = collideWith;
    }
    
}
