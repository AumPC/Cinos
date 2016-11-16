/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author APC
 */
class WorldRenderer {
    
    private Texture BG;
    private Texture Map;
    private CinosGame cinosgame;
    private SpriteBatch batch;
    private World world;
    private Sonic sonic;
    private GameScreen gameScreen;
    public static final int BLOCK_SIZE = 40;
    


    public WorldRenderer(CinosGame cinosgame, World world, GameScreen gameScreen) {
        this.cinosgame = cinosgame;
        this.world = world;
        this.gameScreen = gameScreen;
        batch = cinosgame.batch;
        sonic = world.getSonic();
        BG = new Texture("Windy_Hill_Background.png");
        Map = new Texture("Untitled-2.png");

}
    
    public void render(float delta){
        batch = cinosgame.batch;
        batch.begin();
        batch.draw(BG, gameScreen.gamePositionX(), gameScreen.gamePositionY());
        batch.draw(Map, 0,0);
        sonic.playerSprite.draw(batch);
        batch.end();
    }
}
