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
public class SpecialBox {
    private GameScreen gameScreen;
    private World world;
    private Texture box;
    
    private int x;
    private int y;
    private boolean haveBox;
    private int selected = 0;
    
    public SpecialBox(int x, int y,World world, GameScreen gameScreen) {
        this.x = x;
        this.y = y;
        this.world = world;
        this.gameScreen = gameScreen;
        haveBox = true;
        box = new Texture("Box.png");
    }
    
    public void update(){
        if(haveBox){
            isKeep();
            selected++;
        }
    }
    
    void draw(SpriteBatch batch) {
        if(haveBox){
            batch.draw(box,x, y);
        }
    }

    private void isKeep() {
        float sonicX = world.getSonic().playerSprite.getX()+world.getSonic().playerSprite.getWidth()/2;
        float sonicY = world.getSonic().playerSprite.getY();
        float sonicYMax = world.getSonic().playerSprite.getY()+world.getSonic().playerSprite.getHeight();
        if(sonicX >= x && sonicX <= x+50){
            if(y+47 >= sonicY && y+47 <= sonicYMax){
                gameScreen.numRings += 1;
                haveBox = false;
                getBox();
            }
        }
    }

    private void getBox() {
        if(selected%7==0 || selected%7==1 || selected%7==2){
            gameScreen.score += 100;
        }
        if(selected%7==3){
            gameScreen.score += 250;
        }
        if(selected%7==4){
            gameScreen.score += 500;
        }
        if(selected%7==5){
            gameScreen.score -= 300;
        }
        if(selected%7==6){
            gameScreen.numRings += 20;
        }
    }
}
