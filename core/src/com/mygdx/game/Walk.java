/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

/**
 *
 * @author APC
 */
public class Walk {
    private GameScreen gameScreen;
    private World world;
    public Sonic sonic;
    
    public Walk(GameScreen gameScreen,World world) {
        this.gameScreen = gameScreen;
        this.world = world;
    }
    
    public float baseY(float x) {
        int row = (int) (x)/100;
        int col = 0;
        for(col=0;col<world.baseWalk.length;col++)
        {
            if(world.baseWalk[col].charAt(row)=='#'){
                break;
            }
        }
        if(col>=world.baseWalk.length){
            return -100;
        }
        return 600-col*100;
    }
    
    boolean canMoveX(float x,float y,int dir) {
        if(y == 0 || y == 100 || y == 200 || y == 300 || y == 400 || y == 500){
            y += 1;
        }
        int row = (int) (x)/100;
        int col = (int)(600-y)/100;
        if(world.baseWalk[col].charAt(row)=='#'){
            return false;
        }
        return true;
    }
}
