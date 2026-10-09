package com.zetcode;

public interface Commons {
    
    int WIDTH = 300;
    int HEIGHT = 400;
    int BOTTOM_EDGE = 390;
    // [BUILDER] N_OF_BRICKS = 30 was removed here. It had to match the
    // 5 x 6 loop in Board by hand. Now each level builder decides how many
    // bricks there are, and Board uses bricks.length instead.
    int INIT_PADDLE_X = 200;
    int INIT_PADDLE_Y = 360;
    int INIT_BALL_X = 230;
    int INIT_BALL_Y = 355;    
    int PERIOD = 10;
}
