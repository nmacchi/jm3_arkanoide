/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.classes;

import com.jme3.asset.AssetManager;
import com.jme3.collision.CollisionResults;
import com.jme3.math.FastMath;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.List;

/**
 *
 * @author nicolas
 */
public class Arkanoide {

    private Vector3f position = new Vector3f(0.0f, -2.3f, 1.15f);
    private float scale = new Float(0.2f);
    private Spatial spatial;
    private String model = "Models/Arkanoide/Arkanoide.mesh.xml";
    
    private boolean ballReleased = false;
    
    public Arkanoide() {
    }

    public Arkanoide(AssetManager assetManager) {
        spatial = assetManager.loadModel(model);
        spatial.rotate(0, 90 * FastMath.DEG_TO_RAD, 90 * FastMath.DEG_TO_RAD);
        spatial.setLocalScale(scale);
        spatial.setLocalTranslation(position);
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Spatial getSpatial() {
        return spatial;
    }

    public void setSpatial(Spatial spatial) {
        this.spatial = spatial;
    }

    
    
    public Vector3f shootBall(Ball ball, Node nodeArkanoide) {
        ball.setDirection(new Vector3f(ball.getLocalTranslation().getX() + 1, ball.getLocalTranslation().getY(), ball.getLocalTranslation().getZ() + (-5.65f)).normalize());
        
        //System.out.println(new Vector3f(ball.getLocalTranslation().getX() + 1, ball.getLocalTranslation().getY(), ball.getLocalTranslation().getZ() + (-5.65f)));
        //System.out.println(ball.getLocalTranslation());
        
        Ray ray = new Ray(ball.getWorldTranslation(), ball.getDirection());
        CollisionResults collisions = new CollisionResults();
        
        nodeArkanoide.getParent().collideWith(ray, collisions);

        if(collisions.size() > 0){
            for(int i = 0; i < collisions.size(); i++){
                System.out.println(collisions.getCollision(i).getGeometry().getName() + " - ");
                
                if(!collisions.getCollision(i).getGeometry().getName().equals("ballMesh")){
                    //System.out.println(collisions.getCollision(i).getGeometry().getName());
                    this.setBallReleased(Boolean.TRUE);
                    return collisions.getCollision(i).getContactPoint();
                }
                
            }
         
        }
       
        //System.out.println("llega");
        return new Vector3f(0.0f,0.0f,0.0f);
    }

    public boolean isBallReleased() {
        return ballReleased;
    }

    public void setBallReleased(boolean ballReleased) {
        this.ballReleased = ballReleased;
    }
}
