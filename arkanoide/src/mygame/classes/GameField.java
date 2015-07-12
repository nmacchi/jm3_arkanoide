/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.classes;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;

/**
 *
 * @author nicolas
 */
public class GameField extends Node{
    
    /*Side borders dimensions*/
    private static float S_BORDER_WIDTH = 0.15f;
    private static float S_BORDER_HEIGHT = 0.1f;
    private static float S_BORDER_LENGTH = 3.5f;
    
    /*Top boder dimensions*/
    private static float T_BORDER_WIDTH = 2.2f;
    private static float T_BORDER_HEIGHT = 0.1f;
    private static float T_BORDER_LENGTH = 0.15f;
    
    Floor floor;
    Box topBorder;
    Box leftBorder;
    Box rightBorder;
    Geometry gRightBorder;
    Geometry gLeftBorder;
    Geometry gTopBorder;
    
    Texture boxTexture;
    Material material;
    
    /*Positioning*/
    private static final Vector3f R_BORDER_POSITION = new Vector3f(2.32f, -2.3f, -2.3f);
    private static final Vector3f L_BORDER_POSITION = new Vector3f(-2.35f, -2.3f, -2.3f);
    private static final Vector3f T_BORDER_POSITION = new Vector3f(0.0f, -2.3f, -5.65f);
    
    public GameField(){
        
    }
    
    public GameField(AssetManager assetManager){
        floor = new Floor(assetManager);
        
        topBorder = new Box(T_BORDER_WIDTH, T_BORDER_HEIGHT, T_BORDER_LENGTH);
        leftBorder = new Box(S_BORDER_WIDTH, S_BORDER_HEIGHT, S_BORDER_LENGTH);
        rightBorder = new Box(S_BORDER_WIDTH, S_BORDER_HEIGHT, S_BORDER_LENGTH);
        
        //Texture for the boxes 
        boxTexture = assetManager.loadTexture("Textures/golden.jpg");
        material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.setTexture("ColorMap", boxTexture);
        
        //Create geometries
        gRightBorder = new Geometry("rightBorder",rightBorder);
        gRightBorder.setLocalTranslation(R_BORDER_POSITION);
        gRightBorder.setMaterial(material);
        
        gLeftBorder = new Geometry("leftBorder", leftBorder);
        gLeftBorder.setLocalTranslation(L_BORDER_POSITION);
        gLeftBorder.setMaterial(material);
        
        gTopBorder = new Geometry("topBorder", topBorder);
        gTopBorder.setLocalTranslation(T_BORDER_POSITION);
        gTopBorder.setMaterial(material);
        
        this.attachChild(floor.getGeometry());
        this.attachChild(gRightBorder);
        this.attachChild(gLeftBorder);
        this.attachChild(gTopBorder);
        
    }
    
    
}
