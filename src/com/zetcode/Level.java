package com.zetcode;

/*
 * [BUILDER] Role: PRODUCT
 *
 * A Level is the "complex object" that the Builder pattern constructs.
 * It is just a container for the three parts every level is made of:
 * one paddle, one ball and an array of bricks.
 *
 * BEFORE the refactor these three objects were created directly inside
 * Board.gameInit(). Now a LevelBuilder creates them and stores them here,
 * and Board simply reads them back with the getters.
 *
 * The setters are package-private (no "public") on purpose: only classes in
 * com.zetcode (our builders) should fill a Level. Board only needs the getters.
 */
public class Level {

    private Paddle paddle;
    private Ball ball;
    private Brick[] bricks;

    // ---- used by the builders to put the parts in ----

    void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    void setBall(Ball ball) {
        this.ball = ball;
    }

    void setBricks(Brick[] bricks) {
        this.bricks = bricks;
    }

    // ---- used by Board to take the finished parts out ----

    Paddle getPaddle() {
        return paddle;
    }

    Ball getBall() {
        return ball;
    }

    Brick[] getBricks() {
        return bricks;
    }
}
