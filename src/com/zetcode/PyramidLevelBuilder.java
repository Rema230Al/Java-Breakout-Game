package com.zetcode;

/*
 * [BUILDER]  This class is a CONCRETE BUILDER: the pyramid layout.
 *                                                          (slides 30, 32, 38)
 * WHY IT EXISTS
 *   It is our second layout, and the proof that Builder fits this game.
 *   Slide 30: "The construction process must allow different representation
 *   for the objects that's constructed." Same three steps as
 *   ClassicLevelBuilder, different bricks.
 *
 * IN THE SLIDES
 *   Slide 32 draws two concrete builders (OldStyleRobot, NewStyleRobot).
 *   The slides only show the code of the first. This is our second.
 *   Slide 38: "Easily extended by adding more builder subclasses."
 *
 * WHO USES IT
 *   - Breakout.initUI() creates it when the player picks "Pyramid".
 *   - After that it is only known as a "LevelBuilder". LevelDirector calls
 *     its four methods.
 *
 * THE LAYOUT
 *   5 rows with 2, 3, 4, 5, 6 bricks, centred like the classic grid:
 *
 *              [][]
 *             [][][]
 *            [][][][]
 *           [][][][][]
 *          [][][][][][]
 *
 * GOOD TO KNOW
 *   Adding this layout needed no change to Board or LevelDirector. We
 *   added a class and did not edit the existing ones. (This is called the
 *   Open/Closed principle: open for extension, closed for modification.)
 */
public class PyramidLevelBuilder implements LevelBuilder {

    /*
     * CONSTANTS  ROWS, BRICK_COUNT
     *   What they are : how many rows the pyramid has, and how many bricks
     *                   it has in total.
     *   Why written   : BRICK_COUNT is spelled out as 2 + 3 + 4 + 5 + 6,
     *                   one number per row, because the rows have different
     *                   lengths. If ROWS changes, BRICK_COUNT must too.
     *   Why static    : the value belongs to the class, not to one object.
     *                   "final" means it never changes.
     */
    private static final int ROWS = 5;
    private static final int BRICK_COUNT = 2 + 3 + 4 + 5 + 6;

    /*
     * FIELD  level
     *   The same field as in ClassicLevelBuilder: the Level this builder is
     *   filling.
     */
    private final Level level;

    /*
     * CONSTRUCTOR
     *   Takes         : nothing.
     *   What it does  : creates one EMPTY Level to fill.
     *   Called by     : Breakout.initUI(), when the layout is "Pyramid".
     *   Return type   : none. A constructor never has one.
     */
    public PyramidLevelBuilder() {
        this.level = new Level();
    }

    /*
     * METHOD  buildPaddle()
     *   Why it is here: LevelBuilder says every builder must have this
     *                   step.
     *   Why @Override : the method is declared in LevelBuilder, and this is
     *                   its body. @Override makes the compiler check that
     *                   the name matches the interface exactly.
     *   What it does  : creates the paddle and stores it in the Level. Same
     *                   as in ClassicLevelBuilder.
     *   Called by     : LevelDirector.makeLevel().
     *   Returns       : nothing (void).
     */
    @Override
    public void buildPaddle() {
        level.setPaddle(new Paddle());
    }

    /*
     * METHOD  buildBall()
     *   The same step for the ball. Same as in ClassicLevelBuilder.
     *   Called by     : LevelDirector.makeLevel().
     *   Returns       : nothing (void).
     */
    @Override
    public void buildBall() {
        level.setBall(new Ball());
    }

    /*
     * METHOD  buildBricks()
     *   Why it is here: this is the ONLY step that differs from the classic
     *                   builder. It is what makes the level a pyramid.
     *   What it does  : makes an array for 20 bricks, then goes row by row.
     *                   Row i has i + 2 bricks. A brick is 40 pixels wide,
     *                   so a row that is one brick shorter starts 20 pixels
     *                   (half a brick) further right. That keeps the
     *                   pyramid centred.
     *   Called by     : LevelDirector.makeLevel().
     *   Returns       : nothing (void). The array is stored in the Level.
     */
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

    /*
     * METHOD  getLevel()
     *   Why it is here: this is how the finished product leaves the builder.
     *   Called by     : LevelDirector.getLevel().
     *   Returns       : the Level this builder created and filled.
     */
    @Override
    public Level getLevel() {
        return level;
    }
}
