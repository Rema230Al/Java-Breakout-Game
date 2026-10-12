package com.zetcode;

/*
 * [BUILDER]  This class is a CONCRETE BUILDER: the classic 5 x 6 grid.
 *                                                              (slides 31, 35)
 * WHY IT EXISTS
 *   LevelBuilder only names the steps. Some class has to really do them.
 *   This one builds the ORIGINAL level layout, so the classic game looks
 *   exactly as it did before the refactor.
 *
 * IN THE SLIDES
 *   The ConcreteBuilder box on slide 31, and OldRobotBuilder on slide 35.
 *
 * WHO USES IT
 *   - Breakout.initUI() creates it when the player picks "Classic grid".
 *   - After that it is only known as a "LevelBuilder". LevelDirector calls
 *     its four methods.
 *
 * WHERE THE TWO PATTERNS MEET
 *   This builder writes "new Paddle()", "new Ball()" and "new Brick(x, y)".
 *   Each of those objects gets its picture from the ResourceManager while
 *   it is being created (see loadImage() in each class). The builder does
 *   not know that, and it never touches the ResourceManager itself.
 */
public class ClassicLevelBuilder implements LevelBuilder {

    /*
     * CONSTANTS  ROWS, COLS
     *   What they are : the size of the brick grid, 5 rows of 6 bricks.
     *   Why they exist: the original loop in Board had a plain 5 and 6 in
     *                   it. With names, the array size (ROWS * COLS) and
     *                   the loop always agree.
     *   Why static    : the value belongs to the class, not to one object.
     *                   "final" means it never changes.
     */
    private static final int ROWS = 5;
    private static final int COLS = 6;

    /*
     * FIELD  level
     *   What it is    : the product being built. The build steps fill it,
     *                   and getLevel() hands it over.
     *   Why final     : set once in the constructor, never replaced.
     *   In the slides : "private Robot robot;" on slide 35.
     */
    private final Level level;

    /*
     * CONSTRUCTOR
     *   Takes         : nothing.
     *   What it does  : creates one EMPTY Level to fill. No paddle, ball or
     *                   brick exists yet.
     *   Called by     : Breakout.initUI(), when the layout is "Classic grid".
     *   In the slides : OldRobotBuilder() on slide 35 also creates its
     *                   empty product in the constructor.
     *   Return type   : none. A constructor never has one.
     */
    public ClassicLevelBuilder() {
        this.level = new Level();
    }

    /*
     * METHOD  buildPaddle()
     *   Why it is here: LevelBuilder says every builder must have this
     *                   step.
     *   Why @Override : the method is declared in LevelBuilder, and this is
     *                   its body. @Override makes the compiler check that
     *                   the name matches the interface exactly.
     *   Why public    : methods of an interface are public, so the class
     *                   that implements them must write "public".
     *   What it does  : creates the paddle and stores it in the Level.
     *   Called by     : LevelDirector.makeLevel().
     *   Returns       : nothing (void). The paddle is now inside the Level.
     *   In the slides : buildRobotHead() on slide 35.
     */
    @Override
    public void buildPaddle() {
        level.setPaddle(new Paddle());
    }

    /*
     * METHOD  buildBall()
     *   The same step for the ball: create it, store it in the Level.
     *   @Override and public are there for the same reasons as above.
     *   Called by     : LevelDirector.makeLevel().
     *   Returns       : nothing (void).
     *   Good to know  : buildPaddle() and buildBall() are the same in both
     *                   of our builders. They are still separate steps,
     *                   because every part of the product gets its own
     *                   build step, like every robot part on slide 34.
     */
    @Override
    public void buildBall() {
        level.setBall(new Ball());
    }

    /*
     * METHOD  buildBricks()
     *   Why it is here: this is the step that makes this builder the
     *                   CLASSIC one. The pyramid builder differs only here.
     *   What it does  : makes an array for 30 bricks, then goes through 5
     *                   rows and 6 columns. For each spot it works out the
     *                   position and creates a brick there. Last, it stores
     *                   the full array in the Level.
     *   Called by     : LevelDirector.makeLevel().
     *   Returns       : nothing (void).
     *   Good to know  : the loop and its numbers were moved here unchanged
     *                   from the old Board.gameInit().
     */
    @Override
    public void buildBricks() {

        var bricks = new Brick[ROWS * COLS];

        int k = 0;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                // Same positions as the original game:
                // x = column * 40 + 30, y = row * 10 + 50
                bricks[k] = new Brick(j * 40 + 30, i * 10 + 50);
                k++;
            }
        }

        level.setBricks(bricks);
    }

    /*
     * METHOD  getLevel()
     *   Why it is here: the build steps return nothing, so this is how the
     *                   finished product leaves the builder.
     *   Called by     : LevelDirector.getLevel().
     *   Returns       : the Level this builder created in its constructor
     *                   and filled in the three build steps.
     *   In the slides : getRobot() on slide 35.
     */
    @Override
    public Level getLevel() {
        return level;
    }
}
