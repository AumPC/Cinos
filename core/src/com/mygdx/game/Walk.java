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
    
    public Walk(GameScreen gameScreen,World world) {
        this.gameScreen = gameScreen;
        this.world = world;
    }
    
    public float baseY(float x,float width) {
        int row = (int) (x)/100;
        int row2 = (int) (x+width)/100;
        int col = 0;
        for(col=0;col<world.baseWalk.length;col++)
        {
            if(world.baseWalk[col].charAt(row)=='#' || world.baseWalk[col].charAt(row2)=='#'){
                break;
            }
        }
        if(col>=world.baseWalk.length){
            return -100;
        }
        return 600-col*100;
    }
    
    boolean canMoveX(float x,float y,int dir,float width,int speed) {
        if(y == 0 || y == 100 || y == 200 || y == 300 || y == 400 || y == 500){
            y += 1;
        }
        int row = (int) (x+speed*dir)/100;
        int row2 = (int) (x+width+speed*dir)/100;
        int col = (int)(600-y)/100;
        if(world.baseWalk[col].charAt(row)=='#' || world.baseWalk[col].charAt(row2)=='#'){
            return false;
        }
        return true;
    }
}
