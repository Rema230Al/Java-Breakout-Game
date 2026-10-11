package com.zetcode;

/*
 * [ABSTRACT FACTORY]  Role: CONCRETE PRODUCT, Hard family      (slides 42, 59)
 *
 * WHAT THIS IS
 *   A ball that moves twice as fast as the normal one. Only HardFactory
 *   creates it.
 *
 * IN THE SLIDES
 *   ProductB2 on slide 59: the second family's version of a product.
 *   The game holds it in a variable of type Ball, so its apparent type is
 *   Ball and its actual type is FastBall (slide 42).
 *
 * HOW WE DID IT HERE, AND WHY
 *   Board calls move() once per timer tick. We override move() to run the
 *   original Ball.move() twice, so the ball travels 2 pixels per tick.
 *
 *   Why not just make the step 2 instead of 1? Two reasons:
 *   - Ball.move() finds the walls with "==", for example "if (y == 0)".
 *     The ball starts at y = 355, an odd number. In steps of 2 it would go
 *     355, 353, ..., 1, -1 and never be exactly 0, so it would fly out of
 *     the window. Two steps of 1 keep every wall check working.
 *   - Board's collision code sets the direction to +1 or -1 after a bounce,
 *     which would undo a step of 2 at the first bounce.
 *
 * GOOD TO KNOW
 *   Board never asks "is this a FastBall?". It just calls ball.move() and
 *   Java runs this version. That is polymorphism, and it is the reason
 *   Board did not have to change to support Hard mode.
 */
public class FastBall extends Ball {

    @Override
    void move() {
        super.move();   // first 1-pixel step, with its wall checks
        super.move();   // second 1-pixel step, with its wall checks
    }
}
