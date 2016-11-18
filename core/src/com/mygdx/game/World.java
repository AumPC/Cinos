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
    private int n = 1;
    public SpecialBox specialBox;
    ArrayList<Ring> rings;
    ArrayList<Motobug> motobugs;
    //                           012345678901234567890123456789012345
    public String[] baseWalk = {".....................................",
                                ".....................................",
                                "...........####..................###.",
                                "........#......#...............##....",
                                "......##.............##.....###......",
                                "#####...........#####.######........."};

    World(CinosGame cinosgame,GameScreen gameScreen) {
        SpecialBox specialBox = new SpecialBox(1300,550,this,gameScreen);
        this.cinosgame = cinosgame;
        this.gameScreen = gameScreen;
        walk = new Walk(gameScreen,this);
        sonic = new Sonic(gameScreen,this,walk);
        motobugs = new ArrayList<Motobug>();
        addMotobug();
        rings = new ArrayList<Ring>();
        addRing();
    }
    
    public void addRing(){
        for(int x=350;x<=450;x+=50){
            for(int y=100;y<=200;y+=50){
                rings.add(new Ring(x,y,this,gameScreen));
            }
        }
        for(int x=2400;x<=2700;x+=50){
            for(int y=150;y<=300;y+=50){
                rings.add(new Ring(x,y,this,gameScreen));
            }
        }
        for(int x=2300;x<=3600;x+=50){
            rings.add(new Ring(x,400,this,gameScreen));
        }
        rings.add(new Ring(900,400,this,gameScreen));
        rings.add(new Ring(950,450,this,gameScreen));
        rings.add(new Ring(1000,500,this,gameScreen));
        rings.add(new Ring(1050,550,this,gameScreen));
    }

    public void addMotobug(){
        motobugs.add(new Motobug(gameScreen,1250,510,this,walk));
        motobugs.add(new Motobug(gameScreen,1800,200,this,walk));
        motobugs.add(new Motobug(gameScreen,2500,200,this,walk)); 
    }
    public void update(float delta) {
        sonic.update();
        for(Motobug m : motobugs){
            m.update();
        }
        for(Ring a: rings){
            a.update();
        }
        if(gameScreen.score >= 4200*n){
            addMotobug();
            addRing();
//            SpecialBox specialBox = new SpecialBox(1300,550,this,gameScreen);
            n++;
        }
    }

    Sonic getSonic() {
        return sonic;
    }
    
    SpecialBox getSpecialBox(){
        return specialBox;
    }
}
