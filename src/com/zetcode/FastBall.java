package com.zetcode;

/*
 * [ABSTRACT FACTORY]  This class is a CONCRETE PRODUCT of the Hard family.
 *                                                              (slides 42, 59)
 * WHY IT EXISTS
 *   Hard mode needs a ball that is harder than the normal one. This is it:
 *   a ball that moves twice as fast.
 *
 * IN THE SLIDES
 *   ProductB2 on slide 59, the second family's version of a product.
 *
 * WHO USES IT
 *   - Only HardFactory.createBall() creates it.
 *   - Everyone else holds it in a variable of type Ball. So its apparent
 *     type is Ball and its actual type is FastBall (slide 42).
 *
 * WHAT "extends Ball" GIVES US
 *   A FastBall IS a Ball. It gets the image, the position, the direction
 *   and every method of Ball for free. We only write what is different,
 *   and that is one method.
 *
 * NO CONSTRUCTOR IS WRITTEN
 *   FastBall adds no fields. Java's automatic constructor runs Ball's
 *   constructor, which loads the image and sets the start position.
 */
public class FastBall extends Ball {

    /*
     * METHOD  move()
     *   Why it is here: moving faster is the ONE thing that makes this ball
     *                   different from a normal Ball.
     *   Why @Override : Ball already has move(). This version replaces it
     *                   for FastBall objects. @Override makes the compiler
     *                   check that we really replace Ball.move().
     *   What it does  : runs the original Ball.move() twice. "super" means
     *                   "the version in Ball". So the ball travels 2 pixels
     *                   per tick instead of 1.
     *   Called by     : Board.doGameCycle(), once per timer tick, through a
     *                   variable of type Ball. Board does not know it is
     *                   calling the fast version. That is polymorphism, and
     *                   it is why Board did not change for Hard mode.
     *   Returns       : nothing (void). It only changes the ball's x and y.
     *   Why no public : Ball.move() is written without it, so this is too.
     *   Why not step 2: Ball.move() finds the walls with "==", for example
     *                   "if (y == 0)". The ball starts at y = 355, an odd
     *                   number. In steps of 2 it would go 355, 353, ..., 1,
     *                   -1 and never be exactly 0, so it would fly out of
     *                   the window. Two steps of 1 keep every wall check
     *                   working. Also, Board sets the direction to +1 or -1
     *                   after a bounce, which would undo a step of 2.
     */
    @Override
    void move() {
        super.move();   // first 1-pixel step, with its wall checks
        super.move();   // second 1-pixel step, with its wall checks
    }
}
