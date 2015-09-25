/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.classes;

import com.jme3.asset.AssetManager;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
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
        Vector3f direction = new Vector3f(Vector3f.UNIT_Z).negate();
        
        Quaternion q = new Quaternion();
        q.fromAngleAxis(-FastMath.QUARTER_PI, Vector3f.UNIT_Y);
        q.mult(direction, direction);
        
        ball.setDirection(direction);
        this.setBallReleased(Boolean.TRUE);
    }
    
    
    
    public boolean isBallReleased() {
        return ballReleased;
    }

    public void setBallReleased(boolean ballReleased) {
        this.ballReleased = ballReleased;
    }
}
