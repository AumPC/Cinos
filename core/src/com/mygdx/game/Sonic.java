/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
    private World world;
    public Sprite playerSprite;

    private int speed = 5;
    private boolean isJump1 = false;
    private boolean isJump2 = false;
    private int countY = 0;
    
    public Sonic(GameScreen gameScreen,World world) {
        this.gameScreen = gameScreen;
        this.world = world;
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
    }
    
    private void updatePosition(){
        System.out.println(countY);
        float x = playerSprite.getX();
        float y = playerSprite.getY();
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if(isJump1 == false){
                countY = 10;
                isJump1 = true;
            } else if(isJump2 == false){
                countY = 10;
                isJump2 = true;
            }
        }
        if(countY > 0){
            y += 5;
            countY--;
        } else {
            isJump1 = false;
            isJump2 = false;
            y -= 5;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x -= speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x += speed;
        }
        playerSprite.setPosition(x,y);
        checkPlayerOutOfBound();
    }
    
    private void checkPlayerOutOfBound(){
        float x = playerSprite.getX();
        float y = playerSprite.getY();
        if(x <= 0){
            x = 0;
        }
        else if(x >= world.MAP_X-50){
            x = world.MAP_X-50;
        }
        if(y <= 0){
            y = 0;
        }
        else if(y >= world.MAP_Y-50){
            y = world.MAP_Y - 50;
        }
        playerSprite.setPosition(x, y);
    }

    void draw(SpriteBatch batch) {
        batch.draw(sonicX,gameScreen.gamePositionX(), gameScreen.gamePositionY());
    }
}