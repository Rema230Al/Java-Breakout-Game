package com.zetcode;

/*
 * [ABSTRACT FACTORY] Role: CONCRETE PRODUCT (Hard family)
 *
 * A paddle that moves twice as fast, so the player can keep up with FastBall.
 *
 * Same trick as FastBall: run the original Paddle.move() twice per tick.
 * Paddle.move() already clamps with <= and >=, so the paddle can never
 * leave the window, no matter how many times move() runs.
 */
public class FastPaddle extends Paddle {

    @Override
    void move() {
        super.move();
        super.move();
    }
}
