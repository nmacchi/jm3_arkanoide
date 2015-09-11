/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.classes;

import com.jme3.asset.AssetManager;
import com.jme3.bounding.BoundingBox;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

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

    
    
    public void shootBall(Ball ball, Node nodeArkanoide) {
//        Vector3f extent = ((BoundingBox) spatial.getWorldBound()).getExtent(new Vector3f());
//        
//        System.out.println("Ancho: " + extent.x);
        
        ball.setDirection(new Vector3f(ball.getLocalTranslation().getX() + 1, 0.0f , ball.getLocalTranslation().getZ() - 5.65f));
        
        //System.out.println(new Vector3f(ball.getLocalTranslation().getX() + 1, ball.getLocalTranslation().getY(), ball.getLocalTranslation().getZ() + (-5.65f)));
        //System.out.println(ball.getLocalTranslation());
        this.setBallReleased(Boolean.TRUE);
       
//          return ball.getDirection();
//        System.out.println("llega");
//        return new Vector3f(0.0f,0.0f,0.0f);
    }
    
    
    
    public boolean isBallReleased() {
        return ballReleased;
    }

    public void setBallReleased(boolean ballReleased) {
        this.ballReleased = ballReleased;
    }
}
