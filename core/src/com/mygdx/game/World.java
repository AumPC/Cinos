/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import java.util.ArrayList;

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
    public int numRings = 0;
        private GameOverPage gameOverPage;
    ArrayList<Ring> rings;
    ArrayList<Motobug> motobugs;   
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
        
        motobugs = new ArrayList<Motobug>();
        motobugs.add(new Motobug(gameScreen,800,200,this,walk));
        motobugs.add(new Motobug(gameScreen,1000,200,this,walk));
        
        rings = new ArrayList<Ring>();
        rings.add(new Ring(400,100,this));
        rings.add(new Ring(450,100,this));
        rings.add(new Ring(500,100,this));
    }

    public void update(float delta) {
        if(gameScreen.gameState == 0){
        }
        if(gameScreen.gameState == 1){
        sonic.update();
        for(Motobug m : motobugs){
            m.update();
        }
        for(Ring a: rings){
            a.update();
        }
        }
//        if(gameScreen.gameState == 2){
//            gameOverPage.gameOver();
//            Gdx.gl.glClearColor(0, 0, 0, 1);
//            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        }
    }

    Sonic getSonic() {
        return sonic;
    }
}
