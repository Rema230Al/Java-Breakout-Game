package com.zetcode;

/*
 * [ABSTRACT FACTORY]  This class is a CONCRETE FACTORY: the Hard family.
 *                                                                  (slide 59)
 * WHY IT EXISTS
 *   It is the second family, the one that makes Hard mode. It has the same
 *   three methods as ClassicFactory, but each one creates the harder
 *   version of the object.
 *
 * IN THE SLIDES
 *   ConcreteFactory2 on slide 59. It returns ProductA2 and ProductB2, the
 *   second family.
 *
 * WHO USES IT
 *   - FactoryProducer creates it when the player chooses "Hard".
 *   - After that it is only known as a "GameFactory". The level builders
 *     call its methods without knowing it is the Hard one.
 *
 * THE FAMILY
 *   FastPaddle (2 pixels per tick) + FastBall (2 pixels per tick)
 *   + StrongBrick (2 hits).
 *
 * NO CONSTRUCTOR IS WRITTEN
 *   A factory has no fields to set up, so Java's automatic empty
 *   constructor is enough. "new HardFactory()" in FactoryProducer uses it.
 *
 * GOOD TO KNOW
 *   - This is the only class in the game that uses FastPaddle, FastBall
 *     and StrongBrick by name. Everyone else only sees Paddle, Ball, Brick.
 *   - Why these three must come TOGETHER: a FastBall with a normal Paddle
 *     would be almost impossible to catch. One factory creates the whole
 *     family, so that bad mix cannot happen. Slide 69 calls this benefit
 *     "it promotes consistency among products".
 */
public class HardFactory implements GameFactory {

    /*
     * METHOD  createPaddle()
     *   Why it is here: GameFactory says every factory must have it.
     *   Why @Override : the method is declared in GameFactory, and this is
     *                   its body. @Override makes the compiler check that
     *                   the name and return type match the interface.
     *   What it does  : creates a FastPaddle.
     *   Called by     : buildPaddle() in the level builders.
     *   Returns       : the FastPaddle, but the return type says Paddle.
     *                   That is allowed because a FastPaddle IS a Paddle
     *                   (it extends Paddle). The caller gets a fast paddle
     *                   while only knowing "this is a Paddle". Slide 42
     *                   calls this "same apparent type, different actual
     *                   type".
     */
    @Override
    public Paddle createPaddle() {
        return new FastPaddle();     // handed out as a Paddle
    }

    /*
     * METHOD  createBall()
     *   The same idea, for the ball.
     *   Called by     : buildBall() in the level builders.
     *   Returns       : a FastBall, handed out as a Ball.
     */
    @Override
    public Ball createBall() {
        return new FastBall();       // handed out as a Ball
    }

    /*
     * METHOD  createBrick(x, y)
     *   The same idea, for one brick.
     *   Takes         : the brick's position, passed on to StrongBrick's
     *                   constructor.
     *   Called by     : buildBricks() in the level builders, once per brick.
     *   Returns       : a StrongBrick at (x, y), handed out as a Brick.
     */
    @Override
    public Brick createBrick(int x, int y) {
        return new StrongBrick(x, y); // handed out as a Brick
    }
}
