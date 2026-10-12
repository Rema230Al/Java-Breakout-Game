package com.zetcode;

/*
 * Builds the classic level: 5 rows of 6 bricks, the same as the original
 * game. Breakout creates it when the player picks "Classic grid".
 */
// [BUILDER] CONCRETE BUILDER (slide 35: OldRobotBuilder)
public class ClassicLevelBuilder implements LevelBuilder {

    private static final int ROWS = 5;
    private static final int COLS = 6;

    // [BUILDER] the PRODUCT this builder is filling
    private final Level level;

    // [BUILDER] the builder starts with an EMPTY product (slide 35)
    public ClassicLevelBuilder() {
        this.level = new Level();
    }

    // [BUILDER] BUILD STEP 1: the paddle
    @Override
    public void buildPaddle() {
        level.setPaddle(new Paddle());
    }

    // [BUILDER] BUILD STEP 2: the ball
    @Override
    public void buildBall() {
        level.setBall(new Ball());
    }

    // [BUILDER] BUILD STEP 3: the bricks. This loop was moved here from
    // the old Board.gameInit().
    @Override
    public void buildBricks() {

        var bricks = new Brick[ROWS * COLS];

        int k = 0;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                bricks[k] = new Brick(j * 40 + 30, i * 10 + 50);
                k++;
            }
        }

        level.setBricks(bricks);
    }

    // [BUILDER] GET RESULT: hands over the finished product (slide 31)
    @Override
    public Level getLevel() {
        return level;
    }
}
