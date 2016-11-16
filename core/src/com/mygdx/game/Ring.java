/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author APC
 */
public class Ring {
    private Texture ringPic;
    TextureRegion[] ringPicFrame;
    private int frameCols = 4;	
    private int frameRows = 1;
    public int x;
    public int y;
    public boolean haveRing;
    private GameScreen gameScreen;
    private World world;
    int frame = 0;
    
    public Ring(int x, int y) {
        this.x = x;
        this.y = y;
        setTextureRegion();
        haveRing = true;
    }
    
        
    public void update(){
//        collectRing();
    }
    
//    public void collectRing(){
//        float sonicX = world.getSonic().playerSprite.getX();
//        float sonicY = world.getSonic().playerSprite.getY();
//        float sonicYMax = world.getSonic().playerSprite.getY()+world.getSonic().playerSprite.getHeight();
//        if(sonicX >= x && sonicX <= x+30){
//            if(y >= sonicY && y <= sonicYMax){
//                world.numRings += 1;
//                haveRing = false;
//            }
//        }
//    }
        
    private void setTextureRegion(){
        ringPic = new Texture("ringPic.png");
        TextureRegion[][] tmp = TextureRegion.split(ringPic, ringPic.getWidth()/frameCols
                                                           , ringPic.getHeight()/frameRows);
        ringPicFrame = new TextureRegion[frameRows * frameCols];
        int row1 = 0;
        for(int row2 = 0; row2 < frameRows; row2++){
            for(int col2 = 0; col2 < frameCols; col2++){
                ringPicFrame[row1] = tmp[row2][col2];
                row1++;
            }
        }
    }
    
    void draw(SpriteBatch batch) {
        switch ((frame/5)%4) {
            case 0:
                batch.draw(ringPicFrame[0],x, y);
                break;
            case 1:
                batch.draw(ringPicFrame[1],x, y);
                break;
            case 2:
                batch.draw(ringPicFrame[2],x, y);    
                break;
            case 3:
                batch.draw(ringPicFrame[3],x, y);
                break;
            default:
                break;
        }
        frame++;
    }
}
