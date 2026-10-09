package com.zetcode;

/*
 * [ABSTRACT FACTORY] Role: CONCRETE FACTORY #2 (the Hard family)
 *
 * Creates the Hard-mode objects. This is the ONLY class in the whole game
 * that mentions FastPaddle, FastBall and StrongBrick. Everyone else only
 * sees Paddle, Ball and Brick.
 *
 * Family: FastPaddle + FastBall + StrongBrick.
 *
 * Why these must come TOGETHER (the main point of Abstract Factory):
 * a FastBall with a normal slow Paddle would be almost impossible to catch.
 * Because one factory creates the whole family, that bad mix can't happen.
 */
public class HardFactory implements GameFactory {

    @Override
    public Paddle createPaddle() {
        return new FastPaddle();     // returned as a plain Paddle
    }

    @Override
    public Ball createBall() {
        return new FastBall();       // returned as a plain Ball
    }

    @Override
    public Brick createBrick(int x, int y) {
        return new StrongBrick(x, y); // returned as a plain Brick
    }
}
