package com.zetcode;

/*
 * [ABSTRACT FACTORY]  Role: FACTORY PRODUCER             (slides 58, 60, 67, 68)
 *
 * WHAT THIS IS
 *   The one place that turns the player's choice into a factory. Give it
 *   "Classic" or "Hard" and it gives back a GameFactory.
 *
 * IN THE SLIDES
 *   - Slide 58 calls Abstract Factory a "factory of factories". This class
 *     is that part: a factory whose product is another factory.
 *   - Slide 67: FactoryProducer.getFactory(String choice). Same class name,
 *     same method name, same idea.
 *   - Slide 68: the client writes FactoryProducer.getFactory("SHAPE") and
 *     never writes "new ShapeFactory()". Breakout does the same with us.
 *
 * HOW WE DID IT HERE, AND WHY
 *   - The method is static, like the slide. There is nothing to remember
 *     between calls, so there is no reason to create a FactoryProducer
 *     object first.
 *   - The return type is GameFactory (the interface), so the caller never
 *     sees the names ClassicFactory or HardFactory.
 *   - Slide 67 returns null for an unknown choice. We return the Classic
 *     factory instead. A null factory would crash the game later with a
 *     NullPointerException, and Classic is the original game, so it is the
 *     safe default.
 *
 * GOOD TO KNOW
 *   Adding a third family (say "Easy") needs: one new factory class, one
 *   more "if" here, and one more option in Breakout's dialog. Board and the
 *   level builders stay the same.
 */
public class FactoryProducer {

    public static GameFactory getFactory(String choice) {

        if (choice.equalsIgnoreCase("HARD")) {
            return new HardFactory();
        }

        return new ClassicFactory();
    }
}
