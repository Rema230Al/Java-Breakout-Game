package com.zetcode;

/*
 * [ABSTRACT FACTORY] Role: CONCRETE PRODUCT (Hard family)
 *
 * A brick that needs TWO hits to break.
 *
 * Board calls brick.hit() whenever the ball touches a brick. A normal Brick
 * breaks immediately; this one only counts down the first time.
 * Board never checks "is this a StrongBrick?"; overriding hit() is enough.
 * That is polymorphism doing the work.
 *
 * Known limitation (from the ORIGINAL collision code, not the pattern):
 * on some corner hits the original code does not bounce the ball, so the
 * ball can touch the same brick on the next tick and break it in one pass.
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
