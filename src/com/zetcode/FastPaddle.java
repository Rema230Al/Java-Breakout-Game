package com.zetcode;

/*
 * [ABSTRACT FACTORY]  This class is a CONCRETE PRODUCT of the Hard family.
 *                                                              (slides 42, 59)
 * WHY IT EXISTS
 *   Hard mode has a fast ball, so the player needs a paddle that can keep
 *   up with it. This class exists because of FastBall. The two are designed
 *   to be used together, which is what "family" means in Abstract Factory.
 *
 * IN THE SLIDES
 *   ProductA2 on slide 59, the second family's version of a product.
 *
 * WHO USES IT
 *   - Only HardFactory.createPaddle() creates it.
 *   - Everyone else holds it in a variable of type Paddle (apparent type
 *     Paddle, actual type FastPaddle, slide 42).
 *
 * NO CONSTRUCTOR IS WRITTEN
 *   FastPaddle adds no fields. Java's automatic constructor runs Paddle's
 *   constructor, which loads the image and sets the start position.
 */
public class FastPaddle extends Paddle {

    /*
     * METHOD  move()
     *   Why it is here: moving faster is the ONE thing that makes this
     *                   paddle different from a normal Paddle.
     *   Why @Override : Paddle already has move(). This version replaces it
     *                   for FastPaddle objects. @Override makes the
     *                   compiler check that we really replace Paddle.move().
     *   What it does  : runs the original Paddle.move() twice, so the
     *                   paddle travels 2 pixels per tick instead of 1.
     *   Called by     : Board.doGameCycle(), once per timer tick, through a
     *                   variable of type Paddle.
     *   Returns       : nothing (void). It only changes the paddle's x.
     *   Good to know  : Paddle.move() already stops at the window edges
     *                   with <= and >=, so the paddle cannot leave the
     *                   window however many times move() runs. We did not
     *                   change the speed inside Paddle itself, because
     *                   Paddle is the Classic family's paddle and must keep
     *                   behaving like the original game.
     */
    @Override
    void move() {
        super.move();
        super.move();
    }
}
