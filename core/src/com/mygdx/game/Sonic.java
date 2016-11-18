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
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author APC
 */
public class Sonic {
    private Texture sonicImgLeft;
    private Texture sonicImgRight;
    private TextureRegion[] playerFramesLeft;
    private TextureRegion[] playerFramesRight;
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
        playerSprite.setPosition(100, 150);
    }
    
    public void update(){
        updatePosition();
        playerSprite.setRegion(changePicFrame());
    }

    private void updatePosition(){
        float x = playerSprite.getX();
        float y = playerSprite.getY();
        x = updatePositionX(x,y);
        updatePositionY(y);
        y = checkIsOnBase(x,y);
        if(y == walk.baseY(playerSprite.getX(),playerSprite.getWidth())){
            isJump = false;
        }
        if(y<10){
            gameScreen.gameState = 2;
        }
        playerSprite.setPosition(x,y);
        checkPlayerOutOfBound();
    }
    
     private float updatePositionX(float x,float y){
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            direction = -1;
            x = BorderX(x,y);
            keyLeftPress = true;
        } else {
            keyLeftPress = false;
        }
        isKeyPress(keyLeftPress,keyRightPress);
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            direction = 1;
            keyRightPress = true;
            x = BorderX(x,y);
        } else {
            keyRightPress = false;
        }
        return x;
    }
     
    private float BorderX(float x,float y){
        if(walk.canMoveX(x,y,direction,playerSprite.getWidth(),velocity)) {
            x += velocity*direction;
        }else {
            for(int i=1;i<velocity;i++){
                if(walk.canMoveX(x,y,1,playerSprite.getWidth(),velocity)) {
                    x += (velocity-i)*direction;
                    break;
                }
            }
        }
        return x;
    }
    
    private void updatePositionY(float y){
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
    }
    
    private float checkIsOnBase(float x,float y){
        if(countY > 0){
            y += 7;
            countY--;
        } else {
            if(y == walk.baseY(x,playerSprite.getWidth())){
                isJump1 = false;
                isJump2 = false; 
            } else if(y < walk.baseY(x,playerSprite.getWidth())){
                    y -= 5;
            } else {
                int down;
                for(down = 2;down<=speed;down++){
                    if(y-down <= walk.baseY(x,playerSprite.getWidth()))
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

    private TextureRegion changePicFrame(){
        if(direction == 1){
            if(isJump && velocity == speed){
                return playerFramesRight[6];
            } else {
                switch(velocity){
                    case 6: return playerFramesRight[1];
                    case 7: return playerFramesRight[2];
                    case 8: return playerFramesRight[3];
                    case 9: return playerFramesRight[4];
                    case 10: return playerFramesRight[5];
                    case 11: case 12: return playerFramesRight[6];
                    default: return playerFramesRight[0];
                }
            }
        } else {
            if(isJump && velocity == speed){
                return playerFramesLeft[6];
            } else {
                switch(velocity){
                   case 6: return playerFramesLeft[1];
                    case 7: return playerFramesLeft[2];
                    case 8: return playerFramesLeft[3];
                    case 9: return playerFramesLeft[4];
                    case 10: return playerFramesLeft[5];
                   case 11: case 12: return playerFramesLeft[6];
                    default: return playerFramesLeft[0];
                }
            }
        }
    }    
    
    private void isKeyPress(boolean keyLeftPress,boolean keyRightPress){
        if(keyLeftPress && direction == -1){
            if(!keyRightPress){
                accCount -= 1;
                if(accCount == 0 && velocity < maxSpeed){
                    velocity += acc;
                    accCount = 3;
                }
            } else {
                accCount = 3;
                velocity = speed;
            }
        } else if(keyRightPress && direction == 1){
            if(!keyLeftPress){
                accCount -= 1;
                if(accCount == 0 && velocity < maxSpeed){
                    velocity += acc;
                    accCount = 3;
                }
            } else {
                accCount = 3;
                velocity = speed;
            }
        } else if (!(keyRightPress || keyLeftPress)){
                accCount = 3;
                velocity = speed;        
        }
        System.out.println(velocity);
    }
}