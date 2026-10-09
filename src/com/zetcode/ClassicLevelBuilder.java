package com.zetcode;

/*
 * [BUILDER] Role: CONCRETE BUILDER #1 (the classic 5 x 6 grid)
 *
 * This is the ORIGINAL level layout. The loop and the numbers below were
 * moved here unchanged from the old Board.gameInit(), so the classic game
 * looks exactly like before.
 *
 * Course example equivalent: OldRobotBuilder.
 *
 * [ABSTRACT FACTORY] link:
 * This builder never says "new Ball()". It asks the GameFactory it was
 * given. So:
 *   - the BUILDER decides WHERE things go (positions, how many bricks),
 *   - the FACTORY decides WHICH KIND each thing is (classic or hard).
 */
public class ClassicLevelBuilder implements LevelBuilder {

    // [REFACTOR] These replace the hard-coded "5" and "6" loop limits that
    // used to sit in Board.gameInit().
    private static final int ROWS = 5;
    private static final int COLS = 6;

    private final GameFactory factory;          // decides which kind of objects
    private final Level level = new Level();    // the product being built

    public ClassicLevelBuilder(GameFactory factory) {
        this.factory = factory;
    }

    @Override
    public void buildPaddle() {
        level.setPaddle(factory.createPaddle());
    }

    @Override
    public void buildBall() {
        level.setBall(factory.createBall());
    }

    @Override
    public void buildBricks() {

        // [REFACTOR] The array size is computed from ROWS * COLS, so it can
        // never get out of sync with the loop. Before, Commons.N_OF_BRICKS = 30
        // had to be kept equal to 5 * 6 by hand.
        var bricks = new Brick[ROWS * COLS];

        int k = 0;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                // Same positions as the original game:
                // x = column * 40 + 30, y = row * 10 + 50
                bricks[k] = factory.createBrick(j * 40 + 30, i * 10 + 50);
                k++;
            }
        }

        level.setBricks(bricks);
    }

    @Override
    public Level getLevel() {
        return level;
    }
}
