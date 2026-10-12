package com.zetcode;

/*
 * Runs the build steps in order and hands back the finished Level.
 * It knows WHICH steps to run. It does not know HOW a step is done.
 */
// [BUILDER] DIRECTOR (slide 36: RobotEngineer)
public class LevelDirector {

    // [BUILDER] the builder it gives orders to. Interface type, so any
    // builder works.
    private final LevelBuilder builder;

    // [BUILDER] receives the builder from Board
    public LevelDirector(LevelBuilder builder) {
        this.builder = builder;
    }

    // [BUILDER] THE CONSTRUCTION STEPS, in order (slide 36: makeRobot)
    // POLYMORPHISM: each call runs the code of whichever builder was passed in
    public void makeLevel() {
        builder.buildPaddle();
        builder.buildBall();
        builder.buildBricks();
    }

    // [BUILDER] passes the finished product on to Board (slide 36: getRobot)
    public Level getLevel() {
        return builder.getLevel();
    }
}
