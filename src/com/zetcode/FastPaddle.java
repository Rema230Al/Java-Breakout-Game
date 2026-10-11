package com.zetcode;

/*
 * [ABSTRACT FACTORY]  Role: CONCRETE PRODUCT, Hard family      (slides 42, 59)
 *
 * WHAT THIS IS
 *   A paddle that moves twice as fast, so the player can keep up with the
 *   FastBall. Only HardFactory creates it.
 *
 * IN THE SLIDES
 *   ProductA2 on slide 59: the second family's version of a product.
 *   Apparent type Paddle, actual type FastPaddle (slide 42).
 *
 * HOW WE DID IT HERE, AND WHY
 *   Same idea as FastBall: run the original Paddle.move() twice per tick.
 *   Paddle.move() already stops at the window edges with <= and >=, so the
 *   paddle cannot leave the window however many times move() runs.
 *
 *   We did not change the speed inside Paddle itself, because Paddle is the
 *   Classic family's paddle and must keep behaving like the original game.
 *
 * GOOD TO KNOW
 *   This class exists because of the FastBall. The two are designed to be
 *   used together, which is what "family" means in Abstract Factory.
 */
public class FastPaddle extends Paddle {

    @Override
    void move() {
        super.move();
        super.move();
    }
}
