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
    public Walk walk;
    public float MAP_X = 3600;
    public float MAP_Y = 600;
    
    //                           01234567890123456789012345678901234567890123456789012345678901234567890123456789012345
    public String[] baseWalk = {"......................................................................................",
                                "......................................................................................",
                                "......................................................................................",
                                "......................................................................................",
                                "......###...###.......................................................................",
                                "######...###.........................................................................."};
//    public int startY = 270;
//    public int[] slope =  {  0,  0,-25,  0,  0, 20, 25,  0 , 0,-50,
//                             0,-99,  0,  0,  0,  0,  0,  0,  0,  0,
//                             0,  0,  0,  0,  0,  0};

    World(CinosGame cinosgame,GameScreen gameScreen) {
        this.cinosgame = cinosgame;
        this.gameScreen = gameScreen;
        walk = new Walk(gameScreen,this);
        sonic = new Sonic(gameScreen,this,walk);
    }

    public void update(float delta) {
        sonic.update();
    }

    Sonic getSonic() {
        return sonic;
    }
}
