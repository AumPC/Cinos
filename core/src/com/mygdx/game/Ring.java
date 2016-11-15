/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author APC
 */
public class Ring {
    private Texture ringPic;
    private TextureRegion[] playerFramesLeft;
    private int frameCols = 4;	
    private int frameRows = 1;
    
    private GameScreen gameScreen;
    private World world;
    
    public Ring(GameScreen gameScreen,World world,Walk walk) {
        this.gameScreen = gameScreen;
        this.world = world;
    }
    
    private void setTextureRegion(){
        ringPic = new Texture("ringPic.png");
        TextureRegion[][] tmp = TextureRegion.split(ringPic, ringPic.getWidth()/frameCols
                                                           , ringPic.getHeight()/frameRows);
        playerFramesLeft = new TextureRegion[frameRows * frameCols];
        int row1 = 0;
        for(int row2 = 0; row2 < frameRows; row2++){
            for(int col2 = 0; col2 < frameCols; col2++){
                playerFramesLeft[row1] = tmp[row2][col2];
                row1++;
            }
        }
    }
}
