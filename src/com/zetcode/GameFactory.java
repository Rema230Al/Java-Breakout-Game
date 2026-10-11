package com.zetcode;

/*
 * [ABSTRACT FACTORY]  Role: ABSTRACT FACTORY               (slides 58, 59, 64)
 *
 * WHAT THIS IS
 *   The list of things a factory must be able to create: a paddle, a ball
 *   and a brick. Those three together are one "family". This interface says
 *   WHAT can be created. Each concrete factory decides WHICH class it is.
 *
 * IN THE SLIDES
 *   - Slide 58 (intent): "Provide an interface for creating families of
 *     related or dependent objects without specifying their concrete
 *     classes." This file is that interface.
 *   - Slide 59 (structure): this is the AbstractFactory box. Our three
 *     create methods are its CreateProductA() and CreateProductB().
 *   - Slide 64: the course example writes it as "abstract class
 *     AbstractFactory".
 *
 * HOW WE DID IT HERE, AND WHY
 *   - An interface, not the slide's abstract class. We have no shared code
 *     or fields to inherit, only method names, and that is what an interface
 *     is for. Slide 58 itself says "provide an interface".
 *   - One method per product, like slide 59, instead of one method with a
 *     String such as getShape("CIRCLE") on slide 65. With a method per
 *     product a spelling mistake is a compile error, and no method can
 *     return null.
 *   - Split by FAMILY, like slide 59. The course's coded example splits by
 *     product type (ShapeFactory, ColorFactory), so each factory returns
 *     null for the other type. Here every factory creates every product.
 *   - The return types are Paddle, Ball and Brick, never FastBall and so on.
 *     That is what hides the concrete classes from the rest of the game.
 *
 * GOOD TO KNOW
 *   - Adding a new FAMILY is cheap: one new class that implements this.
 *   - Adding a new KIND of product (say a power-up) is expensive: this
 *     interface AND every concrete factory must change. Slide 69 lists this
 *     as the downside of the pattern.
 */
public interface GameFactory {

    Paddle createPaddle();

    Ball createBall();

    Brick createBrick(int x, int y);
}
