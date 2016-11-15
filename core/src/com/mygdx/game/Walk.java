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
    
    
    public float baseY(float x){
        int row = (int)x/100;
        int col = 0;
        while(col < world.baseWalk.length){
            if(world.baseWalk[col].charAt(row)=='#'){
                break;
            }
            col++;
        }
        System.out.println(row + "  " + col);
        float y;
        if(col==world.baseWalk.length){
            y = 0;
        } else {
//            y = (float) (startYBlock(x) - (x%100)*world.slope[row]/100);
            y = (float) (100*(6-col));
        }
        System.out.println(y);
        return y;
    }
//    private float startYBlock(float x){
//        int slopeBefore = 0;
//        for(int row=0;row<(x/100)-1;row++)
//        {
//            if(world.slope[row]>0){
//                slopeBefore += world.slope[row];
//            }
//        }
//        return world.startY-slopeBefore;
//    }
    
    public float baseX(float x,float y,int dir){
        int row = (int) x/100;
        int col = (int) ((600-y-1)/100);
        if(world.baseWalk[col].charAt(row)=='#'){
            return x-5*dir;
        }
        System.out.println("@" +row + "  " + col);
        return x;
    }
}
