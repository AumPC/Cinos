/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 * @author APC
 */
public class GameScreen extends ScreenAdapter {
    public SpriteBatch batch;
    private CinosGame cinosgame;
    private World world;
    private WorldRenderer worldRenderer;
    private Sonic sonic;
    public OrthographicCamera gameCam; 
    public Viewport gamePort;

    public GameScreen(CinosGame cinosgame) {
        this.cinosgame = cinosgame;
        world = new World(cinosgame,this);
        worldRenderer = new WorldRenderer(cinosgame,world,this);
        sonic = world.getSonic();
        batch = cinosgame.batch;
        
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(world.MAP_X,world.MAP_Y,gameCam);
        gameCam.setToOrtho(false, 800, 600);
    }
    
    @Override
    public void render(float delta) {
        world.update(delta);
        cameraUpdate();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(gameCam.combined);
        worldRenderer.render(delta);
        System.out.println(gamePositionX() + " && "+ gamePositionY());
    }
    
    private void cameraUpdate(){
        float x = world.sonic.playerSprite.getX();
        if(x < (Gdx.graphics.getWidth() / 2)){
            x = (Gdx.graphics.getWidth() / 2);
        }
        else if(x > (world.MAP_X - (Gdx.graphics.getWidth() / 2))){
            x = (world.MAP_X - (Gdx.graphics.getWidth() / 2));
        }
        float y = world.sonic.playerSprite.getY();
        if(y < (Gdx.graphics.getHeight() / 2)){
            y = (Gdx.graphics.getHeight() / 2);
        }
        else if(y > (world.MAP_Y - (Gdx.graphics.getHeight() / 2))){
            y = (world.MAP_Y - (Gdx.graphics.getHeight() / 2));
        }
        gameCam.position.set(x, y, 0);
        System.out.println("POS" + x + " && "+ y);
        gameCam.update();
    }
    
    public float gamePositionX (){
        return gameCam.position.x - (Gdx.graphics.getWidth() / 2);
    }
	
    public float gamePositionY (){
        return gameCam.position.y - (Gdx.graphics.getHeight() / 2);
    }
}