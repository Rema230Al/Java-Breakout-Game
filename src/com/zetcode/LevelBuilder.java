package com.zetcode;

// BUILDER INTERFACE 
public interface LevelBuilder {


    void buildPaddle();

    void buildBall();

    void buildBricks();

    Level getLevel();
}
