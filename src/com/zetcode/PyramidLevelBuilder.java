package com.zetcode;

/*
 * [BUILDER] Role: CONCRETE BUILDER #2 (a pyramid layout)
 *
 * Same building steps as ClassicLevelBuilder, different result. This class
 * exists to show the main benefit of Builder: "the same construction process
 * can create different representations".
 *
 * Adding this layout needed ZERO changes to Board. That is the Open/Closed
 * principle: open for extension (new class), closed for modification.
 *
 * Layout: 5 rows with 2, 3, 4, 5, 6 bricks = 20 bricks, centred like the
 * classic grid:
 *
 *          [][]
 *         [][][]
 *        [][][][]
 *       [][][][][]
 *      [][][][][][]
 */
public class PyramidLevelBuilder implements LevelBuilder {

    private static final int ROWS = 5;
    private static final int BRICK_COUNT = 20;  // 2 + 3 + 4 + 5 + 6

    private final GameFactory factory;
    private final Level level = new Level();

    public PyramidLevelBuilder(GameFactory factory) {
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

        var bricks = new Brick[BRICK_COUNT];

        int k = 0;

        for (int i = 0; i < ROWS; i++) {

            int count = i + 2;                       // row 0 has 2 bricks, row 4 has 6
            int startX = 30 + (ROWS - 1 - i) * 20;   // shift shorter rows right by half a brick per missing brick

            for (int j = 0; j < count; j++) {
                bricks[k] = factory.createBrick(startX + j * 40, i * 10 + 50);
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
