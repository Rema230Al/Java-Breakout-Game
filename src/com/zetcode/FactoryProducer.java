package com.zetcode;

/*
 * [ABSTRACT FACTORY]  This class is the FACTORY PRODUCER.
 *                                                      (slides 58, 60, 67, 68)
 * WHY IT EXISTS
 *   Something has to turn the player's choice into a factory object. That
 *   is this class. Slide 58 calls Abstract Factory a "factory of
 *   factories", and this is that part: a factory whose product is another
 *   factory.
 *
 * IN THE SLIDES
 *   FactoryProducer on slide 67, with the same class name and the same
 *   method name. On slide 68 the client writes
 *   FactoryProducer.getFactory("SHAPE") and never "new ShapeFactory()".
 *
 * WHO USES IT
 *   Only Breakout.initUI(), once, right after the player answers the
 *   difficulty question.
 *
 * NO CONSTRUCTOR IS WRITTEN, AND NO OBJECT IS EVER CREATED
 *   The only method is static, so it is called on the class itself:
 *   FactoryProducer.getFactory(...). Nobody writes "new FactoryProducer()".
 *
 * GOOD TO KNOW
 *   Adding a third family (say "Easy") needs: one new factory class, one
 *   more "if" here, and one more option in Breakout's dialog. Board and the
 *   level builders stay the same.
 */
public class FactoryProducer {

    /*
     * METHOD  getFactory(choice)
     *   Why it is here: it keeps the decision "Classic or Hard" in one
     *                   place, so the names ClassicFactory and HardFactory
     *                   are not written in Breakout.
     *   Why static    : it needs no object. There is nothing to remember
     *                   between calls. Slide 67 does the same.
     *   Takes         : the text of the button the player clicked,
     *                   "Classic" or "Hard".
     *   What it does  : "Hard", in any mix of capital and small letters,
     *                   gives a HardFactory. Anything else gives a
     *                   ClassicFactory.
     *   Called by     : Breakout.initUI().
     *   Returns       : a GameFactory. The return type is the interface, so
     *                   the caller cannot tell which factory it got.
     *   Why not null  : slide 67 returns null for an unknown choice. A null
     *                   factory would crash the game later with a
     *                   NullPointerException. Classic is the original game,
     *                   so it is the safe default.
     */
    public static GameFactory getFactory(String choice) {

        if (choice.equalsIgnoreCase("HARD")) {
            return new HardFactory();
        }

        return new ClassicFactory();
    }
}
