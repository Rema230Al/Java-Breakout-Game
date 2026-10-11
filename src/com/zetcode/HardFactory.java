package com.zetcode;

/*
 * [ABSTRACT FACTORY]  Role: CONCRETE FACTORY #2, the Hard family   (slide 59)
 *
 * WHAT THIS IS
 *   The factory for Hard mode. Same three methods as ClassicFactory, but
 *   each one returns the harder version of the object.
 *
 * IN THE SLIDES
 *   ConcreteFactory2 on slide 59. It returns ProductA2 and ProductB2, the
 *   second family.
 *
 * HOW WE DID IT HERE, AND WHY
 *   - This is the only class in the game that uses FastPaddle, FastBall
 *     and StrongBrick by name. Everyone else only sees Paddle, Ball, Brick.
 *   - Each method's return type is the normal class (for example Ball), but
 *     the object it really creates is the subclass (FastBall). Slide 42
 *     calls this "same apparent type, different actual type".
 *
 * THE FAMILY
 *   FastPaddle (2 pixels per tick) + FastBall (2 pixels per tick)
 *   + StrongBrick (2 hits).
 *
 * GOOD TO KNOW
 *   Why these three must come TOGETHER: a FastBall with a normal Paddle
 *   would be almost impossible to catch. One factory creates the whole
 *   family, so that bad mix cannot happen. Slide 69 calls this benefit
 *   "it promotes consistency among products".
 */
public class HardFactory implements GameFactory {

    @Override
    public Paddle createPaddle() {
        return new FastPaddle();     // handed out as a Paddle
    }

    @Override
    public Ball createBall() {
        return new FastBall();       // handed out as a Ball
    }

    @Override
    public Brick createBrick(int x, int y) {
        return new StrongBrick(x, y); // handed out as a Brick
    }
}
