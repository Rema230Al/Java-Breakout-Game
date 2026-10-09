package com.zetcode;

/*
 * [ABSTRACT FACTORY] Role: CONCRETE PRODUCT (Hard family)
 *
 * A ball that moves twice as fast as the normal one.
 *
 * HOW: Board calls move() once per timer tick. We override move() to run
 * the original Ball.move() TWICE, so the ball travels 2 px per tick.
 *
 * WHY NOT just change the step from 1 to 2?
 * Ball.move() detects walls with "==" (e.g. if (y == 0)). The ball starts
 * at y = 355, an odd number. Moving 2 px at a time it would go 355, 353,
 * ..., 1, -1 and NEVER be exactly 0, so it would fly out of the window.
 * Two 1-pixel steps keep every wall check working.
 * Also, Board's collision code sets directions to +1/-1, which would reset
 * a speed of 2 after the first bounce.
 */
public class FastBall extends Ball {

    @Override
    void move() {
        super.move();   // first 1-pixel step (with wall checks)
        super.move();   // second 1-pixel step (with wall checks)
    }
}
