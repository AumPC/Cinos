/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author APC
 */
class GameOverPage {
    private Texture BGGameOver;
    private Texture restartButton;
    private CinosGame cinosgame;
    private GameScreen gameScreen;
    private SpriteBatch batch;
    private BitmapFont font;
    
    GameOverPage(CinosGame cinosgame,GameScreen gameScreen) {
        this.cinosgame = cinosgame;
        this.batch = cinosgame.batch;
        this.gameScreen = gameScreen;
        BGGameOver = new Texture("gameover.png");
        this.font = new BitmapFont();
    }
    
    public void render(float delta){
        batch.begin();
        batch.draw(BGGameOver, gameScreen.gamePositionX(), gameScreen.gamePositionY());
//        font.draw(batch, "Ring : "+""+ gameScreen.numRings,gameScreen.gamePositionX()+ 350, gameScreen.gamePositionY()+250);
//        font.draw(batch, "Score : "+""+ gameScreen.score,gameScreen.gamePositionX()+ 325, gameScreen.gamePositionY()+300);
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            cinosgame.dispose();
            cinosgame.create();
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.end();
    }
}
