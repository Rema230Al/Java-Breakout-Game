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
 *   This builder never writes "new Ball()". It asks the GameFactory it was
 *   given. So:
 *       the BUILDER decides how many bricks there are and where they go,
 *       the FACTORY decides which KIND of object each one is.
 *
 * DIFFERENT FROM THE SLIDES, AND WHY
 *   The slide's builder writes its parts directly ("Tin Head"). Ours asks
 *   a factory, because we have two kinds of parts (Classic and Hard) and a
 *   layout should not care which kind it is placing.
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
     * FIELD  factory
     *   What it is    : the factory this builder gets every object from.
     *   Why this type : GameFactory is the interface, so this builder works
     *                   with the Classic family and with the Hard family
     *                   without knowing which one it has.
     *   Why final     : set once in the constructor, never replaced.
     */
    private final GameFactory factory;

    /*
     * FIELD  level
     *   What it is    : the product being built. The build steps fill it,
     *                   and getLevel() hands it over.
     *   In the slides : "private Robot robot;" on slide 35.
     */
    private final Level level;

    /*
     * CONSTRUCTOR
     *   Takes         : the factory to get the objects from.
     *   What it does  : stores the factory, and creates one EMPTY Level to
     *                   fill. No paddle, ball or brick exists yet.
     *   Called by     : Breakout.initUI(), which passes in the factory it
     *                   got from FactoryProducer.
     *   Why like this : the builder must not choose Classic or Hard itself,
     *                   so the factory is handed in from outside.
     *   In the slides : OldRobotBuilder() on slide 35 also creates its
     *                   empty product in the constructor.
     *   Return type   : none. A constructor never has one.
     */
    public ClassicLevelBuilder(GameFactory factory) {
        this.factory = factory;
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
     *   What it does  : asks the factory for a paddle and stores it in the
     *                   Level. It never writes "new Paddle()".
     *   Called by     : LevelDirector.makeLevel().
     *   Returns       : nothing (void). The paddle is now inside the Level.
     *   In the slides : buildRobotHead() on slide 35.
     */
    @Override
    public void buildPaddle() {
        level.setPaddle(factory.createPaddle());
    }

    /*
     * METHOD  buildBall()
     *   The same step for the ball: ask the factory, store the result.
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
        level.setBall(factory.createBall());
    }

    /*
     * METHOD  buildBricks()
     *   Why it is here: this is the step that makes this builder the
     *                   CLASSIC one. The pyramid builder differs only here.
     *   What it does  : makes an array for 30 bricks, then goes through 5
     *                   rows and 6 columns. For each spot it works out the
     *                   position and asks the factory for a brick there.
     *                   Last, it stores the full array in the Level.
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
                bricks[k] = factory.createBrick(j * 40 + 30, i * 10 + 50);
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
