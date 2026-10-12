package com.zetcode;

/*
 * A finished level: one paddle, one ball and the bricks. It only holds
 * them. It has no logic and does not know who built it.
 */
// [BUILDER] PRODUCT (slide 33: Robot)
public class Level {

    // the three parts of a level
    private Paddle paddle;
    private Ball ball;
    private Brick[] bricks;

    // setters: the builders put the parts in
    void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    void setBall(Ball ball) {
        this.ball = ball;
    }

    void setBricks(Brick[] bricks) {
        this.bricks = bricks;
    }

    // getters: Board takes the parts out
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
