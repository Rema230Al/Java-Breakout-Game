package com.zetcode;

/*
 * [ABSTRACT FACTORY]  This class is a CONCRETE FACTORY: the Classic family.
 *                                                                  (slide 59)
 * WHY IT EXISTS
 *   GameFactory only names the three create methods. Some class has to
 *   really create the objects. This one creates the ORIGINAL game's
 *   objects, so choosing it gives the original Breakout.
 *
 * IN THE SLIDES
 *   ConcreteFactory1 on slide 59. Every product it returns belongs to the
 *   same family (ProductA1 and ProductB1 in the diagram).
 *
 * WHO USES IT
 *   - FactoryProducer creates it when the player did not choose Hard.
 *   - After that it is only known as a "GameFactory". The level builders
 *     call its methods without knowing it is the Classic one.
 *
 * THE FAMILY
 *   Paddle (1 pixel per tick) + Ball (1 pixel per tick) + Brick (1 hit).
 *
 * NO CONSTRUCTOR IS WRITTEN
 *   A factory has no fields to set up, so Java's automatic empty
 *   constructor is enough. "new ClassicFactory()" in FactoryProducer
 *   uses it.
 */
public class ClassicFactory implements GameFactory {

    /*
     * METHOD  createPaddle()
     *   Why it is here: GameFactory says every factory must have it.
     *   Why @Override : the method is declared in GameFactory, and this is
     *                   its body. @Override makes the compiler check that
     *                   the name and return type match the interface.
     *   Why public    : methods of an interface are public, so the class
     *                   that implements them must write "public".
     *   What it does  : creates a normal Paddle. This is the only line in
     *                   the game that writes "new Paddle()". Before the
     *                   refactor that line was inside Board.gameInit().
     *   Called by     : buildPaddle() in the level builders.
     *   Returns       : the new Paddle.
     */
    @Override
    public Paddle createPaddle() {
        return new Paddle();
    }

    /*
     * METHOD  createBall()
     *   The same as createPaddle(), for the ball. The only "new Ball()".
     *   Called by     : buildBall() in the level builders.
     *   Returns       : a new, normal Ball.
     */
    @Override
    public Ball createBall() {
        return new Ball();
    }

    /*
     * METHOD  createBrick(x, y)
     *   The same idea, for one brick. The only "new Brick(...)".
     *   Takes         : the brick's position. It is passed straight on to
     *                   Brick's constructor.
     *   Called by     : buildBricks() in the level builders, once per brick.
     *   Returns       : a new, normal Brick at (x, y).
     */
    @Override
    public Brick createBrick(int x, int y) {
        return new Brick(x, y);
    }
}
