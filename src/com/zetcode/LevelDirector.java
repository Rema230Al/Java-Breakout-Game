package com.zetcode;

// DIRECTOR 
public class LevelDirector {

    
    private final LevelBuilder builder;

    public LevelDirector(LevelBuilder builder) {
        this.builder = builder;
    }

  
    // runs the code of whichever builder was passed in
    public void makeLevel() {
        builder.buildPaddle();
        builder.buildBall();
        builder.buildBricks();
    }


    public Level getLevel() {
        return builder.getLevel();
    }
}
