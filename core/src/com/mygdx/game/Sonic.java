/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
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

    private int speed = 5;
    private boolean jump1 = false;
    private boolean jump2 = false;
    private int countY = 0;
    
    float minX = 0;
    float minY = 0;
    float maxX = 3200;
    float maxY = 800;
    
    private Pixmap pixMap;
    Texture mapBlack;
    
    public Sonic(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
//        position = new Vector2(x,y);
        setTextureRegion();
        setUpSprite();
        mapBlack = new Texture("Untitled-12.png");

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
//        checkWalk();
    }
    
//    public void checkWalk(){
//        pixMap = mapBlack.getTextureData().consumePixmap();
//        int f = pixMap.getPixel(200, 100);
//    pixMap.dispose();
//        System.out.println("Color" + f);
//    }
    
    private void updatePosition(){
        System.out.println(countY);
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if(jump1 == false){
                countY = 10;
                jump1 = true;
            } else if(jump2 == false){
                countY = 10;
                jump2 = true;
            }
        }
        if(countY > 0){
            playerSprite.setPosition(playerSprite.getX(), playerSprite.getY()+5);
            countY--;
        } else {
            jump1 = false;
            jump2 = false;
            playerSprite.setPosition(playerSprite.getX(), playerSprite.getY()-5);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            playerSprite.setPosition(playerSprite.getX()- speed , playerSprite.getY());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            playerSprite.setPosition(playerSprite.getX()+ speed , playerSprite.getY());
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
    
    
    
    
    
    
    
    
    
    

    
    
    
    
    

    
