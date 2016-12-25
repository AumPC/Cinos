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
    private GameScreen gameScreen;
    private World world;
    private Texture ringPic;
    private TextureRegion[] ringPicFrame;
    private int frameCols = 4;	
    private int frameRows = 1;
    
    private int x;
    private int y;
    private boolean haveRing;
    private int frame = 0;
    
    public Ring(int x, int y, World world, GameScreen gameScreen) {
        this.x = x;
        this.y = y;
        this.world = world;
        this.gameScreen = gameScreen;
        setTextureRegion();
        haveRing = true;
    }
    
    public void update(){
        if(haveRing){
            collectRing();
        }
    }
    
    public void collectRing(){
        float sonicX;
        sonicX = world.getSonic().playerSprite.getX() + world.getSonic().playerSprite.getWidth()/2;
        float sonicY = world.getSonic().playerSprite.getY();
        float sonicYMax = world.getSonic().playerSprite.getY() + world.getSonic().playerSprite.getHeight();
        if(sonicX >= x && sonicX <= x+30){
            if(y >= sonicY && y <= sonicYMax){
                gameScreen.numRings += 1;
                haveRing = false;
                gameScreen.score += 50;
            }
        }
    }
        
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
        if(haveRing){
            if((frame/5)%4 == 0) {
                    batch.draw(ringPicFrame[0], x, y);
            } else if((frame/5)%4 == 1) {
                    batch.draw(ringPicFrame[1], x, y);
            } else if((frame/5)%4 == 2) {
                    batch.draw(ringPicFrame[2], x, y);    
            } else if((frame/5)%4 == 3) {
                    batch.draw(ringPicFrame[3], x, y);
            }
            frame++;
        }
    }
}
