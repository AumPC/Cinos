/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author APC
 */
public class Sonic {
    private Texture sonicImgLeft;
    private Texture sonicX;
    private TextureRegion[] playerFramesLeft;
    private int frameCols = 10;	
    private int frameRows = 1;
    private GameScreen gameScreen;
    public Sprite playerSprite;
    
    public static final int UP = 3;
    public static final int RIGHT = 1;
    public static final int LEFT = 2;
    public static final int STILL = 0;
    private int speed = 5;
    private int nextdir;
    private int changex[] = {0,1,-1};
    
    float minX = 0;
    float minY = 0;
    float maxX = 3200;
    float maxY = 800;
    
    public Sonic(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
//        position = new Vector2(x,y);
        setTextureRegion();
        setUpSprite();
    }    
    
    private void setTextureRegion(){
        sonicImgLeft = new Texture("sonic_left.gif");
        sonicX = new Texture("sonic_left.gif");
        TextureRegion[][] tmp = TextureRegion.split(sonicImgLeft, sonicImgLeft.getWidth()/frameCols
								, sonicImgLeft.getHeight()/frameRows);
        playerFramesLeft = new TextureRegion[frameRows * frameCols];
        int row1 = 0;
        for(int row2 = 0; row2 < frameRows; row2++){
            for(int col2 = 0; col2 < frameCols; col2++){
                playerFramesLeft[row1] = tmp[row2][col2];
                row1++;
            }
        }
    }
    
    private void setUpSprite(){
	playerSprite = new Sprite(playerFramesLeft[0]);
        playerSprite.setPosition(100, 300);
    }
    
    public void update(){
        updatePosition();
//        updateAttack();
    }
    
    public void nextdirection(int dir){
        nextdir = dir;
    }
    
    private void updatePosition(){
        if(nextdir!=3) {
            playerSprite.setPosition(playerSprite.getX()+ speed * changex[nextdir] , playerSprite.getY()) ;
            if(playerSprite.getY()<=500)
                playerSprite.setPosition(playerSprite.getX(), playerSprite.getY()+5) ;
        } else {
            playerSprite.setPosition(playerSprite.getX(), playerSprite.getY()-45);
        }
        checkPlayerOutOfBound();
    }
    
    private void checkPlayerOutOfBound(){
        if(playerSprite.getX() <= minX){
            playerSprite.setPosition(minX, playerSprite.getY());
        }
        else if(playerSprite.getX() >= maxX){
            playerSprite.setPosition(maxX, playerSprite.getY());
        }
        if(playerSprite.getY() <= minY){
            playerSprite.setPosition(playerSprite.getX(), minY);
        }
        else if(playerSprite.getY() >= maxY){
            playerSprite.setPosition(playerSprite.getX(), maxY);
        }
    }

    void draw(SpriteBatch batch) {
        batch.draw(sonicX,gameScreen.gamePositionX(), gameScreen.gamePositionY());
    }
}
    
    
    
    
    
    
    
    
    
    

    
    
    
    
    

    
