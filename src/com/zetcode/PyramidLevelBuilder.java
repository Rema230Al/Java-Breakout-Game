package com.zetcode;

/*
 * [BUILDER]  Role: CONCRETE BUILDER #2, a pyramid layout   (slides 30, 32, 38)
 *
 * WHAT THIS IS
 *   A second layout. Same three steps as ClassicLevelBuilder, different
 *   bricks.
 *
 * IN THE SLIDES
 *   - Slide 30: "The construction process must allow different
 *     representation for the objects that's constructed." One process, two
 *     builders, two different levels. This class is our proof of that, and
 *     it is the main reason Builder fits this game.
 *   - Slide 32 draws two concrete builders (OldStyleRobot, NewStyleRobot).
 *     The slides only show the code of the first. This is our second.
 *   - Slide 38: "Easily extended by adding more builder subclasses."
 *
 * HOW WE DID IT HERE, AND WHY
 *   - 5 rows with 2, 3, 4, 5, 6 bricks, centred like the classic grid:
 *
 *              [][]
 *             [][][]
 *            [][][][]
 *           [][][][][]
 *          [][][][][][]
 *
 *   - Row i has i + 2 bricks. A brick is 40 pixels wide, so a row that is
 *     one brick shorter starts 20 pixels (half a brick) further right.
 *     That is what keeps the pyramid centred.
 *   - BRICK_COUNT is written out as 2 + 3 + 4 + 5 + 6 because the rows
 *     have different lengths. If ROWS changes, BRICK_COUNT must change too.
 *
 * GOOD TO KNOW
 *   Adding this layout needed no change to Board or LevelDirector. We
 *   added a class and did not edit the existing ones. (This is called the
 *   Open/Closed principle: open for extension, closed for modification.)
 */
public class PyramidLevelBuilder implements LevelBuilder {

    private static final int ROWS = 5;
    private static final int BRICK_COUNT = 2 + 3 + 4 + 5 + 6;

    private final GameFactory factory;   // decides which kind of objects
    private final Level level;           // the product being built

    public PyramidLevelBuilder(GameFactory factory) {
        this.factory = factory;
        this.level = new Level();        // start with an empty level (slide 35)
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
            int startX = 30 + (ROWS - 1 - i) * 20;   // shorter rows start further right

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
