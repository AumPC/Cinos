/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

/**
 *
 * @author APC
 */
public class GameScreen extends ScreenAdapter {
    private CinosGame cinosgame;
    private World world;
    private WorldRenderer worldRenderer;
    private Sonic sonic;
            
//    
//    private timeutils x;            
            
    public GameScreen(CinosGame cinosgame) {
        this.cinosgame = cinosgame;
        world = new World(cinosgame);
        worldRenderer = new WorldRenderer(cinosgame,world);
        sonic = world.getSonic();
    }
    
    @Override
    public void render(float delta) {
       update(delta);
       Gdx.gl.glClearColor(0, 0, 0, 1);
       Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       worldRenderer.render(delta);
    }
    
    public void update(float delta) {
        world.update(delta);
        updateSonicDirection();
        sonic.update();
    }
    
    private void updateSonicDirection() {
        if(Gdx.input.isKeyPressed(Input.Keys.UP) && sonic.position.y>=400) {
            sonic.nextdirection(Sonic.UP);
        }else
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            sonic.nextdirection(Sonic.LEFT);
        }else
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            sonic.nextdirection(Sonic.RIGHT);
        }else
            sonic.nextdirection(Sonic.STILL);
    }
}