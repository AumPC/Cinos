package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author APC
 */
public class Motobug {
    private Texture motobugPicLeft;
    private Texture motobugPicRight;
    private World world;
    private Walk walk;
    private GameScreen gameScreen;
    
    private int x;
    private int y;
    private int speed  = 5;
    private int movement = 0;
    private boolean direction = true;
    private boolean exist;
    private int hitDelay = 20;
    
    public Motobug (GameScreen gameScreen, int x, int y, World world, Walk walk) {
        this.world = world;
        this.gameScreen = gameScreen;
        this.walk = walk;
        this.x = x;
        this.y = y;
        setTexture();
        exist = true;
    }
    
    private void setTexture(){
        motobugPicLeft = new Texture("motobugleft.gif");
        motobugPicRight = new Texture("motobugright.gif");
        if(y > walk.baseY(x, 48)){
            y = (int)walk.baseY(x, 48);
        }
    }
    
    public void update(){
        if(exist){
            move();
            if(isHit() && hitDelay <= 0){
                hitBySonic();
               hitDelay = 20;
            }
            hitDelay--;
        }
    }
    
    private void move(){
        if((movement < 40) && (direction == true)){
            movement++;
            
            x += speed;
        } else if(movement > -40){
            movement--;
            x -= speed;
        }
        if((movement == 40) || (movement == -40)){
            direction = !direction;
        }
    }
    
    private void hitBySonic(){
        if((world.getSonic().isJump == true) && (world.getSonic().playerSprite.getY() < y+48)){
            exist = false;
            gameScreen.score += 500;
        } else {
            if(gameScreen.numRings > 0){
                gameScreen.numRings = 0;
            } else {
                System.out.println("GAMEOVER");
                gameScreen.gameState = 2;
            }
        }
    }
    
    public boolean isHit(){
        float sonicX = world.getSonic().playerSprite.getX() + world.getSonic().playerSprite.getWidth()/2;
        float sonicY = world.getSonic().playerSprite.getY();
        float sonicYMax = world.getSonic().playerSprite.getY() - world.getSonic().playerSprite.getHeight();
        if(sonicX >= x && sonicX <= x+48){
            if(y <= sonicY && y >= sonicYMax){
                return true;
            }
        }
        return false;
    }
        
    void draw(SpriteBatch batch) {
        if(exist){
            if(direction == true){
                batch.draw(motobugPicRight, x, y);
           } else {
                batch.draw(motobugPicLeft, x, y);
            }
        }
    }
}
