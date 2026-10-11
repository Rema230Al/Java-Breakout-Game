package com.zetcode;

/*
 * [BUILDER]  Role: PRODUCT                                     (slides 31, 33)
 *
 * WHAT THIS IS
 *   A Level is the finished thing the Builder pattern produces. It is a
 *   simple container for the three parts every level has: one paddle, one
 *   ball and an array of bricks.
 *
 * IN THE SLIDES
 *   - The Product box on slide 31.
 *   - Robot on slide 33: private fields, one setter and one getter per
 *     part, and nothing else. Level has the same shape, with paddle, ball
 *     and bricks in place of head, torso, arms and legs.
 *
 * HOW WE DID IT HERE, AND WHY
 *   - Before the refactor these three objects were created directly inside
 *     Board.gameInit(). Now a LevelBuilder creates them and stores them
 *     here, and Board reads them back with the getters.
 *   - The builders are the only ones that call the setters. Board is the
 *     only one that calls the getters.
 *   - The methods have no "public" in front, where the slide's Robot uses
 *     public. We followed the rest of this game (Ball.move(),
 *     Brick.isDestroyed() and so on are written the same way). All our
 *     classes are in one package, so it makes no difference to how the
 *     code works.
 *
 * GOOD TO KNOW
 *   The product has no logic. It does not know it was "built", and it does
 *   not know about builders or factories. All the knowledge about how to
 *   make a level is in the builders.
 */
public class Level {

    private Paddle paddle;
    private Ball ball;
    private Brick[] bricks;

    // ---- used by the builders to put the parts in ----

    void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    void setBall(Ball ball) {
        this.ball = ball;
    }

    void setBricks(Brick[] bricks) {
        this.bricks = bricks;
    }

    // ---- used by Board to take the finished parts out ----

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
