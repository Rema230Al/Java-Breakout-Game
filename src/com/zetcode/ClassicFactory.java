package com.zetcode;

/*
 * [ABSTRACT FACTORY]  Role: CONCRETE FACTORY #1, the Classic family (slide 59)
 *
 * WHAT THIS IS
 *   The factory for the original game. Ask it for a paddle, a ball or a
 *   brick and you get the same objects the game always had.
 *
 * IN THE SLIDES
 *   ConcreteFactory1 on slide 59. It implements every create method, and
 *   every product it returns belongs to the same family (ProductA1 and
 *   ProductB1 in the diagram).
 *
 * HOW WE DID IT HERE, AND WHY
 *   Each method is one line: "return new ...". These are the only lines in
 *   the game that write "new Paddle()", "new Ball()" and "new Brick(...)".
 *   Before the refactor they were written inside Board.gameInit().
 *
 * THE FAMILY
 *   Paddle (1 pixel per tick) + Ball (1 pixel per tick) + Brick (1 hit).
 */
public class ClassicFactory implements GameFactory {

    @Override
    public Paddle createPaddle() {
        return new Paddle();
    }

    @Override
    public Ball createBall() {
        return new Ball();
    }

    @Override
    public Brick createBrick(int x, int y) {
        return new Brick(x, y);
    }
}
