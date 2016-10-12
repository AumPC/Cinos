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
    private Sonic sonic;
    
    World(CinosGame cinosgame) {
        this.cinosgame = cinosgame;
        sonic = new Sonic(60,500);
    }

    public void update(float delta) {
//        pacman.update();
    }
    
    Sonic getSonic() {
        return sonic;
    }
//    Maze getMaze() {
//        return maze;
//    }
}
