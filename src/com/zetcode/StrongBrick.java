package com.zetcode;

/*
 * [ABSTRACT FACTORY]  This class is a CONCRETE PRODUCT of the Hard family.
 *                                                              (slides 42, 59)
 * WHY IT EXISTS
 *   Hard mode needs a brick that is harder than the normal one. This is
 *   it: a brick that needs two hits to break.
 *
 * IN THE SLIDES
 *   A second-family product on slide 59, like FastBall and FastPaddle.
 *
 * WHO USES IT
 *   - Only HardFactory.createBrick() creates it.
 *   - Everyone else holds it in a variable of type Brick (apparent type
 *     Brick, actual type StrongBrick, slide 42).
 */
public class StrongBrick extends Brick {

    /*
     * FIELD  hitsLeft
     *   What it is    : how many more hits this brick can take. It starts
     *                   at 2 and counts down.
     *   Why it is here: how many hits a brick needs is the brick's own
     *                   business, so the count lives in the brick and not
     *                   in Board.
     *   Why private   : only hit() below reads and changes it.
     */
    private int hitsLeft = 2;

    /*
     * CONSTRUCTOR
     *   Takes         : the brick's position on the screen.
     *   What it does  : passes x and y up to Brick's constructor with
     *                   "super(x, y)". Brick's constructor sets the
     *                   position and loads the image.
     *   Called by     : HardFactory.createBrick(x, y).
     *   Why written   : Brick has no empty constructor. It always needs x
     *                   and y. So Java cannot add an automatic constructor
     *                   here, and we must write one that calls super(x, y).
     *   Return type   : none. A constructor never has one.
     */
    public StrongBrick(int x, int y) {
        super(x, y);
    }

    /*
     * METHOD  hit()
     *   Why it is here: taking two hits is the ONE thing that makes this
     *                   brick different from a normal Brick.
     *   Why @Override : Brick already has hit(), which breaks the brick at
     *                   once. This version replaces it for StrongBrick
     *                   objects.
     *   What it does  : counts down by one. When the count reaches 0 it
     *                   marks the brick as destroyed.
     *   Called by     : Board.checkCollision(), each time the ball touches
     *                   a brick that is not destroyed yet. Board never
     *                   checks which kind of brick it has.
     *   Returns       : nothing (void).
     *   Good to know  : - Board only calls hit() on bricks that are not
     *                     destroyed, so hitsLeft never goes below 0.
     *                   - Known limit, from the ORIGINAL collision code and
     *                     not from the pattern: on some corner hits Board
     *                     does not turn the ball around, so the ball
     *                     touches the same brick again on the next tick and
     *                     the brick can break in one pass.
     */
    @Override
    void hit() {
        hitsLeft--;

        if (hitsLeft == 0) {
            setDestroyed(true);
        }
    }
}
