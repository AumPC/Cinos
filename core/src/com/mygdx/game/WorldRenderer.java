/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    private BitmapFont font;

    public WorldRenderer(CinosGame cinosgame, World world, GameScreen gameScreen) {
        this.cinosgame = cinosgame;
        this.world = world;
        this.gameScreen = gameScreen;
        this.batch = cinosgame.batch;
        sonic = world.getSonic();
        BG = new Texture("Windy_Hill_Background.png");
        Map = new Texture("Untitled-2.png");        
        this.font = new BitmapFont();
        font.getData().setScale((float) 1.5); 
}
    
    public void render(float delta){
        batch = cinosgame.batch;
        batch.begin();
        if(gameScreen.gameState == 1){
            batch.draw(BG, gameScreen.gamePositionX(), gameScreen.gamePositionY());
            batch.draw(Map, 0,0);
            sonic.playerSprite.draw(batch);
            for(Motobug m : world.motobugs){
                m.draw(batch);
            }
            this.font.draw(batch, "Ring : "+""+ gameScreen.numRings,gameScreen.gamePositionX()+ 50, gameScreen.gamePositionY()+575);
            this.font.draw(batch, "Score : "+""+ gameScreen.score,gameScreen.gamePositionX()+ 650, gameScreen.gamePositionY()+575);
            for(Ring a : world.rings){
                a.draw(batch);
            }
        }
        batch.end();
    }
}
