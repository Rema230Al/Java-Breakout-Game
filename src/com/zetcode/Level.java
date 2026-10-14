package com.zetcode;

// BUILDER PRODUCT 
public class Level {

    private Paddle paddle;
    private Ball ball;
    private Brick[] bricks;

    // called by the builder
    void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    void setBall(Ball ball) {
        this.ball = ball;
    }

    void setBricks(Brick[] bricks) {
        this.bricks = bricks;
    }

    //called by Board
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
