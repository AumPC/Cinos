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
class World {
    private CinosGame cinosgame;
    private GameScreen gameScreen;
    public Sonic sonic;
    
    public float MAP_X = 4000;
    public float MAP_Y = 600;
    
    World(CinosGame cinosgame,GameScreen gameScreen) {
        this.cinosgame = cinosgame;
        this.gameScreen = gameScreen;
        sonic = new Sonic(gameScreen);
    }

    public void update(float delta) {
        sonic.update();
    }
    
    Sonic getSonic() {
        return sonic;
    }
}
