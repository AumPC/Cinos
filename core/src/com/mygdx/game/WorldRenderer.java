/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author APC
 */
class WorldRenderer {
    private Texture SonicImg;
    private CinosGame cinosgame;
    private SpriteBatch batch;
    private World world;
    private Sonic sonic;
    public static final int BLOCK_SIZE = 40;

    public WorldRenderer(CinosGame cinosgame, World world) {
        this.cinosgame = cinosgame;
        batch = cinosgame.batch;
        this.world = world;
        SonicImg = new Texture("Sonic.png");
        sonic = world.getSonic();
    }
    
    public void render(float delta){
        batch = cinosgame.batch;
        batch.begin();
        Vector2 pos = sonic.getPosition();
        batch.draw(SonicImg, pos.x, CinosGame.HEIGHT - pos.y);
        batch.end();
    }
}
