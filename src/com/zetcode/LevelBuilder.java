package com.zetcode;

/*
 * [BUILDER] Role: BUILDER (the interface)
 *
 * Declares the STEPS needed to build a Level, one method per part,
 * plus getLevel() to collect the finished result.
 *
 * Every layout (classic grid, pyramid, ...) implements these same steps in
 * its own way. Because they all share this interface, the LevelDirector and
 * Board can work with ANY layout without knowing which one it is.
 *
 * Course example equivalent: RobotBuilder.
 */
public interface LevelBuilder {

    void buildPaddle();     // step 1: create the paddle

    void buildBall();       // step 2: create the ball

    void buildBricks();     // step 3: create and position all the bricks

    Level getLevel();       // hand back the finished product
}
