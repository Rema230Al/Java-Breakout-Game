package com.zetcode;

/*
 * Builds the pyramid level: 5 rows with 2, 3, 4, 5 and 6 bricks, centred.
 * Breakout creates it when the player picks "Pyramid". It is the new
 * layout, added without changing Board or LevelDirector.
 */
// [BUILDER] CONCRETE BUILDER, the second one (slide 32)
public class PyramidLevelBuilder implements LevelBuilder {

    private static final int ROWS = 5;
    private static final int BRICK_COUNT = 2 + 3 + 4 + 5 + 6;

    // [BUILDER] the PRODUCT this builder is filling
    private final Level level;

    // [BUILDER] the builder starts with an EMPTY product
    public PyramidLevelBuilder() {
        this.level = new Level();
    }

    // [BUILDER] BUILD STEP 1: the paddle (same as the classic builder)
    @Override
    public void buildPaddle() {
        level.setPaddle(new Paddle());
    }

    // [BUILDER] BUILD STEP 2: the ball (same as the classic builder)
    @Override
    public void buildBall() {
        level.setBall(new Ball());
    }

    // [BUILDER] BUILD STEP 3: the bricks. The ONLY step that differs from
    // the classic builder.
    @Override
    public void buildBricks() {

        var bricks = new Brick[BRICK_COUNT];

        int k = 0;

        for (int i = 0; i < ROWS; i++) {

            int count = i + 2;                       // row 0 has 2 bricks, row 4 has 6
            int startX = 30 + (ROWS - 1 - i) * 20;   // shorter rows start further right

            for (int j = 0; j < count; j++) {
                bricks[k] = new Brick(startX + j * 40, i * 10 + 50);
                k++;
            }
        }

        level.setBricks(bricks);
    }

    // [BUILDER] GET RESULT: hands over the finished product
    @Override
    public Level getLevel() {
        return level;
    }
}
