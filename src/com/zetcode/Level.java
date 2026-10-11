package com.zetcode;

/*
 * [BUILDER]  This class is the PRODUCT.                        (slides 31, 33)
 *
 * WHY IT EXISTS
 *   The Builder pattern builds something. This is the something: a finished
 *   level. It is a simple container for the three parts every level has:
 *   one paddle, one ball and an array of bricks. It has no logic.
 *
 * IN THE SLIDES
 *   The Product box on slide 31, and Robot on slide 33: private fields,
 *   one setter and one getter per part, and nothing else.
 *
 * WHO USES IT
 *   - A level builder creates one empty Level and fills it with the setters.
 *   - LevelDirector passes it on without touching it.
 *   - Board.gameInit() takes the parts out with the getters.
 *
 * NO CONSTRUCTOR IS WRITTEN
 *   A new Level starts empty, so there is nothing for a constructor to do.
 *   Java adds an empty one automatically, and "new Level()" in the builders
 *   uses it. The slide writes the same empty constructor out by hand:
 *   "public Robot() { }".
 *
 * GOOD TO KNOW
 *   The product does not know it was "built", and it does not know about
 *   builders or factories. All the knowledge about how to make a level is
 *   in the builders.
 */
public class Level {

    /*
     * FIELDS  paddle, ball, bricks
     *   What they are : the three parts of a level.
     *   Why private   : nobody reaches in directly. Parts go in through the
     *                   setters and come out through the getters.
     *   Why this type : Paddle, Ball and Brick are the normal classes. In
     *                   Hard mode the objects are really a FastPaddle, a
     *                   FastBall and StrongBricks. They still fit here,
     *                   because each of those IS a Paddle, a Ball, a Brick.
     */
    private Paddle paddle;
    private Ball ball;
    private Brick[] bricks;

    /*
     * THE THREE SETTERS  setPaddle(), setBall(), setBricks()
     *   Why they exist: the builder creates the parts one step at a time,
     *                   so it needs a way to store each one.
     *   Takes         : the finished part.
     *   Called by     : the build step with the matching name, in
     *                   ClassicLevelBuilder and PyramidLevelBuilder:
     *                   buildPaddle(), buildBall() and buildBricks().
     *   Returns       : nothing (void). They only store the part.
     *   In the slides : setRobotHead(), setRobotTorso() and so on, slide 33.
     *   Why no public : the slide's Robot uses public. We followed the rest
     *                   of this game (Ball.move() and Brick.isDestroyed()
     *                   are written the same way). All our classes are in
     *                   one package, so it makes no difference here.
     */
    void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    void setBall(Ball ball) {
        this.ball = ball;
    }

    void setBricks(Brick[] bricks) {
        this.bricks = bricks;
    }

    /*
     * THE THREE GETTERS  getPaddle(), getBall(), getBricks()
     *   Why they exist: Board needs to take the finished parts out.
     *   Called by     : Board.gameInit(), once each, right after the
     *                   director hands over the Level.
     *   Returns       : the part that the builder stored.
     *   In the slides : the four getters of Robot on slide 33.
     */
    Paddle getPaddle() {
        return paddle;
    }

    Ball getBall() {
        return ball;
    }

    Brick[] getBricks() {
        return bricks;
    }
}
