/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author APC
 */
public class Sonic {
    public static final int UP = 3;
    public static final int RIGHT = 1;
    public static final int LEFT = 2;
    public static final int STILL = 0;
    public Vector2 position;
    private int speed = 5;
    private int nextdir;
    private int changex[] = {0,1,-1};
    
    public Sonic(int x, int y) {
        position = new Vector2(x,y);
    }    
    public Vector2 getPosition() {
        return position;
    }
    public void nextdirection(int dir){
        nextdir = dir;
    }
    
    public void update() {
        if(nextdir!=3) {
            position.x += speed * changex[nextdir];
            if(position.y<=500)
                position.y += 2;
        } else {
            position.y -= 40;
        }
    }
}
