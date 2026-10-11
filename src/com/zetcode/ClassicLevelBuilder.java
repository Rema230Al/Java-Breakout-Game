package com.zetcode;

/*
 * [BUILDER]  Role: CONCRETE BUILDER #1, the classic 5 x 6 grid (slides 31, 35)
 *
 * WHAT THIS IS
 *   The builder for the ORIGINAL level layout. It carries out each step
 *   from LevelBuilder and stores the result in its Level.
 *
 * IN THE SLIDES
 *   - The ConcreteBuilder box on slide 31 (BuildPart() and GetResult()).
 *   - OldRobotBuilder on slide 35: it creates an empty product in its
 *     constructor, each build method fills in one part with a setter, and
 *     getRobot() returns the product. This class does the same with Level.
 *
 * HOW WE DID IT HERE, AND WHY
 *   - The brick loop and its numbers were moved here unchanged from the
 *     old Board.gameInit(), so the classic game looks exactly as before.
 *   - ROWS and COLS replace the plain 5 and 6 that were in that loop. The
 *     array size is ROWS * COLS, so the size and the loop always agree.
 *   - This builder never writes "new Ball()". It asks the GameFactory it
 *     was given. This is where our two patterns meet:
 *         the BUILDER decides how many bricks there are and where they go,
 *         the FACTORY decides which KIND of object each one is.
 *   - The slide's builder writes its parts directly ("Tin Head"). Ours
 *     asks a factory, because we have two kinds of parts (Classic and
 *     Hard) and a layout should not care which kind it is placing.
 *
 * GOOD TO KNOW
 *   buildPaddle() and buildBall() are the same in both of our builders.
 *   Only buildBricks() differs between layouts. They are still separate
 *   steps because the paddle and the ball are parts of a Level, and every
 *   part gets its own build step, like every robot part on slide 34.
 */
public class ClassicLevelBuilder implements LevelBuilder {

    private static final int ROWS = 5;
    private static final int COLS = 6;

    private final GameFactory factory;   // decides which kind of objects
    private final Level level;           // the product being built

    public ClassicLevelBuilder(GameFactory factory) {
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
