package com.zetcode;

/*
 * The list of steps for building a Level. The director gives its orders
 * through this interface, so it works with any layout.
 */
// [BUILDER] BUILDER interface (slide 34: RobotBuilder)
public interface LevelBuilder {

    // [BUILDER] THE BUILD STEPS, one per part of the product
    void buildPaddle();

    void buildBall();

    void buildBricks();

    // [BUILDER] GET RESULT: hands over the finished product (slide 31)
    Level getLevel();
}
