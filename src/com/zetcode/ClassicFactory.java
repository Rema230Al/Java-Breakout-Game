package com.zetcode;

/*
 * [ABSTRACT FACTORY] Role: CONCRETE FACTORY #1 (the Classic family)
 *
 * Creates the ORIGINAL game objects, exactly as the game had before the
 * refactor. Choosing this factory gives you the original Breakout.
 *
 * Family: Paddle (1 px per tick) + Ball (1 px per tick) + Brick (1 hit).
 */
public class ClassicFactory implements GameFactory {

    @Override
    public Paddle createPaddle() {
        return new Paddle();
    }

    @Override
    public Ball createBall() {
        return new Ball();
    }

    @Override
    public Brick createBrick(int x, int y) {
        return new Brick(x, y);
    }
}
