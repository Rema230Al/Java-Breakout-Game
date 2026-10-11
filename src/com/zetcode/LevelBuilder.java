package com.zetcode;

/*
 * [BUILDER]  Role: BUILDER (the interface)                     (slides 31, 34)
 *
 * WHAT THIS IS
 *   The list of steps needed to build a Level: one method per part, plus
 *   getLevel() to collect the finished result.
 *
 * IN THE SLIDES
 *   - The Builder box on slide 31, with its BuildPart() method.
 *   - RobotBuilder on slide 34: buildRobotHead(), buildRobotTorso(),
 *     buildRobotArms(), buildRobotLegs(), getRobot().
 *     Ours: buildPaddle(), buildBall(), buildBricks(), getLevel().
 *
 * HOW WE DID IT HERE, AND WHY
 *   - Three build methods, because a Level has three parts.
 *   - An interface, like the slide. The builders share method names only,
 *     not code.
 *   - Every layout implements these same steps in its own way. Because
 *     they all share this interface, LevelDirector and Board can work with
 *     ANY layout without knowing which one it is.
 *
 * GOOD TO KNOW
 *   - Slide 38 says the builder interface "reflects the process of
 *     constructing the product more than any other creational patterns".
 *     You can read the recipe for a level right here.
 *   - How this differs from Abstract Factory: a factory method RETURNS its
 *     object straight away (createBall() gives you a ball). A build method
 *     returns nothing (void). The parts collect inside the builder, and you
 *     take the whole Level at the end with getLevel().
 */
public interface LevelBuilder {

    void buildPaddle();     // step: create the paddle

    void buildBall();       // step: create the ball

    void buildBricks();     // step: create and position all the bricks

    Level getLevel();       // hand back the finished product
}
