package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
    int movement = 0;
    int x;
    int y;
    int speed  = 5;
    boolean direction = true;
    boolean exist;
    private GameScreen gameScreen;
    
    public Motobug (GameScreen gameScreen,int x,int y,World world,Walk walk) {
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
        if(y>walk.baseY(x)){
            y = (int)walk.baseY(x);
        }
    }
    
    public void update(){
        move();
        if(isHit()){
            hitBySonic();
        }
    }
    
    private void move(){
        if(movement<50 && direction == true){
            movement++;
            
            x += speed;
        } else if(movement>-50){
            movement--;
            x -= speed;
        }
        if(movement == 50 || movement == -50){
            direction = !direction;
        }
    }
    
    private void hitBySonic(){
        if(world.getSonic().isJump==true){
            exist = false;
        } else {
            if(world.numRings > 0){
                world.numRings = 0;
            } else {
                System.out.println("GAMEOVER");
            }
        }
    }
    
    public boolean isHit(){
        float sonicX = world.getSonic().playerSprite.getX() + world.getSonic().playerSprite.getWidth()/2;
        float sonicY = world.getSonic().playerSprite.getY();
        float sonicYMax = world.getSonic().playerSprite.getY()+world.getSonic().playerSprite.getHeight();
        if(sonicX >= x && sonicX <= x+100){
            if(y >= sonicY && y <= sonicYMax){
                return true;
            }
        }
        return false;
    }
        
    void draw(SpriteBatch batch) {
        if(direction == true){
            batch.draw(motobugPicRight,x, y);
        } else {
            batch.draw(motobugPicLeft,x, y);
        }
    }
}
