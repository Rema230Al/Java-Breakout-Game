package com.zetcode;

/*
 * [BUILDER]  This class is the DIRECTOR.                   (slides 31, 32, 36)
 *
 * WHY IT EXISTS
 *   The Builder pattern needs someone who runs the building steps. That is
 *   the director. It tells the builder "build the paddle, build the ball,
 *   build the bricks" and then hands back the finished Level. It does not
 *   know HOW any step is done. That is the builder's job.
 *
 * IN THE SLIDES
 *   The Director box on slide 31, and RobotEngineer on slide 36.
 *
 * WHO USES IT
 *   Only Board.gameInit(). It creates one director, calls makeLevel(), and
 *   then calls getLevel().
 */
public class LevelDirector {

    /*
     * FIELD  builder
     *   What it is    : the builder this director gives its orders to.
     *   Why this type : LevelBuilder is the interface, so the same director
     *                   works with the classic grid and with the pyramid.
     *   Why final     : it is set once in the constructor and never
     *                   replaced.
     */
    private final LevelBuilder builder;

    /*
     * CONSTRUCTOR
     *   Takes         : the builder to use.
     *   What it does  : only stores it. Nothing is built yet.
     *   Called by     : Board.gameInit(), which passes on the builder that
     *                   Breakout gave to Board.
     *   Why like this : the director must not pick the layout itself, so
     *                   the builder is handed in from outside. Same as
     *                   RobotEngineer(RobotBuilder) on slide 36.
     *   Return type   : none. A constructor never has one.
     */
    public LevelDirector(LevelBuilder builder) {
        this.builder = builder;
    }

    /*
     * METHOD  makeLevel()
     *   Why it is here: this is the director's whole job. The list of steps
     *                   is written in this one place, so nobody who wants a
     *                   level can forget a step.
     *   What it does  : calls the three build steps on the builder.
     *   Called by     : Board.gameInit().
     *   Returns       : nothing (void). The parts pile up inside the
     *                   builder, and getLevel() below hands them over.
     *   In the slides : makeRobot() on slide 36.
     *   Good to know  : our three steps do not depend on each other, so any
     *                   order would work. Slide 36 also builds in no special
     *                   order (arms, head, legs, torso).
     */
    public void makeLevel() {
        builder.buildPaddle();
        builder.buildBall();
        builder.buildBricks();
    }

    /*
     * METHOD  getLevel()
     *   Why it is here: makeLevel() returns nothing, so there must be a way
     *                   to get the result out.
     *   What it does  : asks the builder for its Level and passes it on.
     *                   The director never creates or changes the Level.
     *   Called by     : Board.gameInit(), right after makeLevel().
     *   Returns       : the finished Level, with the paddle, the ball and
     *                   the bricks inside.
     *   In the slides : getRobot() on slide 36.
     */
    public Level getLevel() {
        return builder.getLevel();
    }
}
