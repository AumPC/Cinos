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
    public static final int UP = 1;
    public static final int RIGHT = 2;
    public static final int LEFT = 3;
    public static final int STILL = 0;
    private static final int [][] DIRECT = new int [][] {
        {0,0},
        {0,1},
        {1,0},
        {-1,0}};
    private Vector2 position;
    private int speed = 5;
 
    public Sonic(int x, int y) {
        position = new Vector2(x,y);
    }    
    public Vector2 getPosition() {
        return position;    
    }
}
