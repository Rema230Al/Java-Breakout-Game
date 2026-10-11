package com.zetcode;

/*
 * [ABSTRACT FACTORY]  This interface is the ABSTRACT FACTORY.
 *                                                          (slides 58, 59, 64)
 * WHY IT EXISTS
 *   The pattern needs one type that says "a factory can create a paddle, a
 *   ball and a brick" without saying which classes those are. This is that
 *   type. Slide 58: "Provide an interface for creating families of related
 *   or dependent objects without specifying their concrete classes."
 *
 * IN THE SLIDES
 *   The AbstractFactory box on slide 59. The course example on slide 64 is
 *   "abstract class AbstractFactory".
 *
 * WHO USES IT
 *   - ClassicFactory and HardFactory implement it.
 *   - FactoryProducer returns one of them as a GameFactory.
 *   - Breakout holds it in a GameFactory variable and passes it on.
 *   - The level builders keep it in a field and call its three methods.
 *
 * NO CONSTRUCTOR, NO FIELDS, NO METHOD BODIES
 *   An interface only lists methods. Each method below ends with ";" and
 *   has no { } body. The bodies are in ClassicFactory and HardFactory. The
 *   methods are public automatically, so "public" is not written here.
 *
 * DIFFERENT FROM THE SLIDES, AND WHY
 *   - An interface, not the slide's abstract class. We have no shared code
 *     or fields to inherit, only method names, and that is what an
 *     interface is for. Slide 58 itself says "provide an interface".
 *   - One method per product, like slide 59, instead of one method with a
 *     String such as getShape("CIRCLE") on slide 65. With a method per
 *     product a spelling mistake is a compile error, and no method can
 *     return null.
 *   - Split by FAMILY, like slide 59. The course's coded example splits by
 *     product type (ShapeFactory, ColorFactory), so each factory returns
 *     null for the other type. Here every factory creates every product.
 *
 * GOOD TO KNOW
 *   - Adding a new FAMILY is cheap: one new class that implements this.
 *   - Adding a new KIND of product (say a power-up) is expensive: this
 *     interface AND every factory must change. Slide 69 lists this as the
 *     downside of the pattern.
 */
public interface GameFactory {

    /*
     * METHOD  createPaddle()
     *   Why it is here: a family has a paddle, so every factory must be
     *                   able to create one.
     *   Called by     : buildPaddle() in the level builders.
     *   Returns       : a Paddle. The type is the normal class on purpose.
     *                   HardFactory really returns a FastPaddle, but the
     *                   caller only sees "Paddle". That is what hides the
     *                   concrete classes from the rest of the game.
     *   In the slides : CreateProductA() on slide 59.
     */
    Paddle createPaddle();

    /*
     * METHOD  createBall()
     *   The same idea, for the ball.
     *   Called by     : buildBall() in the level builders.
     *   Returns       : a Ball (really a FastBall when it is HardFactory).
     */
    Ball createBall();

    /*
     * METHOD  createBrick(x, y)
     *   The same idea, for one brick.
     *   Takes         : where the brick goes on the screen. The builder
     *                   works out x and y. The factory only creates.
     *   Called by     : buildBricks() in the level builders, once per brick.
     *   Returns       : a Brick (really a StrongBrick from HardFactory).
     */
    Brick createBrick(int x, int y);
}
