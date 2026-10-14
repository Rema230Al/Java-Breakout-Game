package com.zetcode;

// CONCRETE BUILDER 1 
public class ClassicLevelBuilder implements LevelBuilder {

    private static final int ROWS = 5;
    private static final int COLS = 6;

    // PRODUCT being built
    private final Level level;


    public ClassicLevelBuilder() {
        this.level = new Level();
    }

    // STEP 1
    @Override
    public void buildPaddle() {
        level.setPaddle(new Paddle());
    }

    // STEP 2
    @Override
    public void buildBall() {
        level.setBall(new Ball());
    }

    // STEP 3
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

    
    @Override
    public Level getLevel() {
        return level;
    }
}
