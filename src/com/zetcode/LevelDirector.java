package com.zetcode;

/*
 * [BUILDER]  Role: DIRECTOR                                (slides 31, 32, 36)
 *
 * WHAT THIS IS
 *   The class that runs the building steps. It tells the builder "build
 *   the paddle, build the ball, build the bricks" and then hands back the
 *   finished Level. It does not know HOW any step is done. That is the
 *   concrete builder's job.
 *
 * IN THE SLIDES
 *   - The Director box on slide 31, with its Construct() method.
 *   - RobotEngineer on slide 36. makeLevel() is makeRobot(), getLevel() is
 *     getRobot(), and the builder comes in through the constructor in the
 *     same way.
 *
 * HOW WE DID IT HERE, AND WHY
 *   - The field's type is the interface LevelBuilder, so this one director
 *     works for every layout.
 *   - Why have a director at all? So the list of steps is written in ONE
 *     place. Whoever wants a level calls makeLevel() and cannot forget a
 *     step, for example building the bricks but no ball.
 *
 * GOOD TO KNOW
 *   - Our three steps do not depend on each other, so any order would
 *     work. The director's job here is to make sure ALL the steps run, not
 *     to protect a special order. Slide 36 is the same: makeRobot() builds
 *     arms, head, legs, torso in no particular order.
 *   - The director never creates the Level and never changes it. It only
 *     passes along the one the builder made.
 */
public class LevelDirector {

    private final LevelBuilder builder;

    public LevelDirector(LevelBuilder builder) {
        this.builder = builder;
    }

    // Runs every building step.
    public void makeLevel() {
        builder.buildPaddle();
        builder.buildBall();
        builder.buildBricks();
    }

    // Returns the product the builder assembled.
    public Level getLevel() {
        return builder.getLevel();
    }
}
