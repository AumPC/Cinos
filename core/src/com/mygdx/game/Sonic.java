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
    private Texture sonicImgRight;
//    private Texture sonicImgUp;
    private TextureRegion[] playerFramesLeft;
    private TextureRegion[] playerFramesRight;
//    private TextureRegion[] playerFramesUp;
    private int frameCols = 9;	
    private int frameRows = 1;
    private GameScreen gameScreen;
    private World world;
    public Sprite playerSprite;
    private Walk walk;
    
    private int speed = 5;
    private int acc = 1;
    private int accCount = 3;
    private int velocity = speed;
    private int maxSpeed = 12;
    private boolean isJump1 = false;
    private boolean isJump2 = false;
    private int countY = 0;
    private int keyUpDelay =0;
    public boolean isJump = false;
    private int direction = 1;
    private boolean keyUpPress = false;
    private boolean keyLeftPress = false;
    private boolean keyRightPress = false;

    public Sonic(GameScreen gameScreen,World world,Walk walk) {
        this.gameScreen = gameScreen;
        this.world = world;
        setTextureRegion();
        setUpSprite();
        this.walk = walk;
        direction = 1;
    }
    
    private void setTextureRegion(){
        sonicImgLeft = new Texture("sonic_left.gif");
        sonicImgRight = new Texture("sonic_right.gif");
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
        TextureRegion[][] tmp2 = TextureRegion.split(sonicImgRight, sonicImgRight.getWidth()/frameCols
								, sonicImgRight.getHeight()/frameRows);
        playerFramesRight = new TextureRegion[frameRows * frameCols];
        int row3 = 0;
        for(int row2 = 0; row2 < frameRows; row2++){
            for(int col2 = 0; col2 < frameCols; col2++){
                playerFramesRight[row3] = tmp2[row2][col2];
                row3++;
            }
        }
    }
    
    private void setUpSprite(){
	playerSprite = new Sprite(playerFramesRight[0]);
        playerSprite.setPosition(100, 450);
    }
    
    public void update(){
        updatePosition();
    }
    
    private void updatePosition(){
        float x = playerSprite.getX();
        float y = playerSprite.getY();
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if(walk.canMoveX(x,y,-1))
                x -= velocity;
            keyLeftPress = true;
            accCount -= 1;
            if(accCount == 0){
                velocity += acc;
            }
        } else {
            keyLeftPress = false;
            accCount = 3;
            velocity = speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if(walk.canMoveX(x,y,1))
                x += velocity;
            keyRightPress = true;
            if(accCount == 0){
                velocity += acc;
            System.out.println(velocity);
        } else {
            keyRightPress = false;
                        accCount = 10;
            velocity = speed;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP) && keyUpDelay == 0) {
            isJump = true;
            if(isJump1 == false){
                countY = 20;
                isJump1 = true;
                keyUpDelay = 10;
            } else if(isJump2 == false){
                countY = 20;
                isJump2 = true;
            }
        }
        if(keyUpDelay > 0){
            keyUpDelay -= 1;
        }
        y = checkIsOnBase(y);
        playerSprite.setPosition(x,y);
        if(y == walk.baseY(playerSprite.getX())){
            isJump = false;
        }
        checkPlayerOutOfBound();
    }
     
    private float checkIsOnBase(float y){
        if(countY > 0){
            y += 7;
            countY--;
        } else {
            if(y <= walk.baseY(playerSprite.getX())){
                isJump1 = false;
                isJump2 = false; 
            } else {
                int down;
                for(down = 2;down<=speed;down++){
                    if(y-down <= walk.baseY(playerSprite.getX()))
                        break;
                }
                y -= down-1;
            }
        }
        return y;
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
        
    private void draw(SpriteBatch batch) {
        batch.draw(changePicFrame(),gameScreen.gamePositionX(), gameScreen.gamePositionY());
    }
    
    private TextureRegion changePicFrame(){
        if(direction == 1){
            switch(velocity){
                case 6: return playerFramesRight[1];
                case 7: return playerFramesRight[2];
                case 8: return playerFramesRight[3];
                case 9: return playerFramesRight[4];
                case 10: return playerFramesRight[5];
                case 11: return playerFramesRight[6];
                case 12: return playerFramesRight[7];
                default: return playerFramesRight[0];
            }
        } else {
            switch(velocity){
                case 6: return playerFramesLeft[1];
                case 7: return playerFramesLeft[2];
                case 8: return playerFramesLeft[3];
                case 9: return playerFramesLeft[4];
                case 10: return playerFramesLeft[5];
                case 11: return playerFramesLeft[6];
                case 12: return playerFramesLeft[7];
                default: return playerFramesLeft[0];
            }
        }
    }
}