package com.zetcode;

// CONCRETE BUILDER 2 
public class PyramidLevelBuilder implements LevelBuilder {

    private static final int ROWS = 5;
    private static final int BRICK_COUNT = 2 + 3 + 4 + 5 + 6;

    //PRODUCT being built
    private final Level level;

    
    public PyramidLevelBuilder() {
        this.level = new Level();
    }

    // STEP 1
    @Override
    public void buildPaddle() {
        level.setPaddle(new Paddle());
    }

    //STEP 2
    @Override
    public void buildBall() {
        level.setBall(new Ball());
    }

    //STEP 3
    @Override
    public void buildBricks() {

        var bricks = new Brick[BRICK_COUNT];

        int k = 0;

        for (int i = 0; i < ROWS; i++) {

            int count = i + 2;                       
            int startX = 30 + (ROWS - 1 - i) * 20;   

            for (int j = 0; j < count; j++) {
                bricks[k] = new Brick(startX + j * 40, i * 10 + 50);
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
