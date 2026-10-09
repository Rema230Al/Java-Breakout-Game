package com.zetcode;

/*
 * [BUILDER] Role: DIRECTOR
 *
 * The Director knows the ORDER in which the building steps must be called.
 * It does not know HOW each step is done; that is the concrete builder's job.
 *
 * Why have a Director at all?
 *  - The step order lives in ONE place instead of in every client.
 *  - Every builder is used exactly the same way, so no client can forget a
 *    step (e.g. building bricks but forgetting the ball).
 *
 * Course example equivalent: RobotEngineer.
 */
public class LevelDirector {

    // The director works with the INTERFACE, so it accepts any layout.
    private final LevelBuilder builder;

    public LevelDirector(LevelBuilder builder) {
        this.builder = builder;
    }

    // Runs the building steps in a fixed order.
    public void makeLevel() {
        builder.buildPaddle();
        builder.buildBall();
        builder.buildBricks();
    }

    // Returns the product the builder just assembled.
    public Level getLevel() {
        return builder.getLevel();
    }
}
