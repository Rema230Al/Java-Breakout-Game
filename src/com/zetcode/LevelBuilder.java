package com.zetcode;

/*
 * [BUILDER]  This interface is the BUILDER.                    (slides 31, 34)
 *
 * WHY IT EXISTS
 *   The pattern needs one type that lists the steps for building a Level,
 *   so the director can give its orders without knowing which layout it is
 *   talking to. This is that list: one method per part, plus getLevel() to
 *   collect the result.
 *
 * IN THE SLIDES
 *   The Builder box on slide 31, and RobotBuilder on slide 34:
 *   buildRobotHead(), buildRobotTorso(), buildRobotArms(), buildRobotLegs()
 *   and getRobot().
 *
 * WHO USES IT
 *   - ClassicLevelBuilder and PyramidLevelBuilder implement it.
 *   - Breakout holds the chosen builder in a variable of this type.
 *   - Board and LevelDirector each keep it in a field of this type.
 *   - LevelDirector calls all four methods.
 *
 * NO CONSTRUCTOR, NO FIELDS, NO METHOD BODIES
 *   An interface only lists methods. Each method below ends with ";" and
 *   has no { } body. The bodies are in the two concrete builders. The
 *   methods are public automatically, so "public" is not written here.
 *
 * GOOD TO KNOW
 *   - Slide 38 says the builder interface "reflects the process of
 *     constructing the product more than any other creational patterns".
 *     You can read the recipe for a level right here.
 *   - A build method returns nothing. The parts collect inside the
 *     builder, and you take the whole Level at the end with getLevel().
 */
public interface LevelBuilder {

    /*
     * METHOD  buildPaddle()
     *   Why it is here: a level has a paddle, so building one is a step.
     *   Called by     : LevelDirector.makeLevel().
     *   Returns       : nothing (void). The builder keeps the paddle inside
     *                   the Level it is filling.
     *   In the slides : buildRobotHead() and the other build methods on
     *                   slide 34.
     */
    void buildPaddle();

    /*
     * METHOD  buildBall()
     *   The same kind of step, for the ball.
     *   Called by     : LevelDirector.makeLevel().
     *   Returns       : nothing (void).
     */
    void buildBall();

    /*
     * METHOD  buildBricks()
     *   The step that differs between layouts. It creates all the bricks
     *   and decides where each one goes.
     *   Called by     : LevelDirector.makeLevel().
     *   Returns       : nothing (void).
     */
    void buildBricks();

    /*
     * METHOD  getLevel()
     *   Why it is here: the build steps return nothing, so there must be a
     *                   way to collect the finished product.
     *   Called by     : LevelDirector.getLevel(), which passes the Level on
     *                   to Board.
     *   Returns       : the Level the builder has been filling.
     *   In the slides : getRobot() on slide 34, GetResult() on slide 31.
     */
    Level getLevel();
}
