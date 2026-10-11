package com.zetcode;

/*
 * [ABSTRACT FACTORY]  Role: CONCRETE PRODUCT, Hard family      (slides 42, 59)
 *
 * WHAT THIS IS
 *   A brick that needs two hits to break. Only HardFactory creates it.
 *
 * IN THE SLIDES
 *   A second-family product on slide 59, like FastBall and FastPaddle.
 *   Apparent type Brick, actual type StrongBrick (slide 42).
 *
 * HOW WE DID IT HERE, AND WHY
 *   Board calls brick.hit() whenever the ball touches a brick. A normal
 *   Brick breaks at once. This class overrides hit() to count down, and
 *   only breaks when the count reaches 0.
 *
 *   The count lives here and not in Board, because how many hits a brick
 *   needs is the brick's own business. Board never checks which kind of
 *   brick it has.
 *
 * GOOD TO KNOW
 *   - Board only calls hit() on bricks that are not destroyed yet, so
 *     hitsLeft never goes below 0.
 *   - Known limit, from the ORIGINAL collision code and not from the
 *     pattern: on some corner hits Board does not turn the ball around, so
 *     the ball touches the same brick again on the next tick and the brick
 *     can break in one pass.
 */
public class StrongBrick extends Brick {

    private int hitsLeft = 2;

    public StrongBrick(int x, int y) {
        super(x, y);
    }

    @Override
    void hit() {
        hitsLeft--;

        if (hitsLeft == 0) {
            setDestroyed(true);
        }
    }
}
