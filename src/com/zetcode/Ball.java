package com.zetcode;

import javax.swing.ImageIcon;

/*
 * [ABSTRACT FACTORY]  This class is a PRODUCT: the normal ball.
 *                                                              (slides 42, 59)
 * WHY IT MATTERS FOR THE PATTERN
 *   It does two jobs:
 *   1. It is the Classic family's ball, the one ClassicFactory creates.
 *   2. It is the TYPE the rest of the game uses for any ball. HardFactory
 *      creates a FastBall but hands it out as a Ball.
 *
 * IN THE SLIDES
 *   Slide 42 says products have a "common ancestor, same apparent type,
 *   different actual type". Ball is that common, apparent type. It stands
 *   in for the AbstractProduct box on slide 59.
 *
 * WHO USES IT
 *   - ClassicFactory.createBall() creates it.
 *   - Level carries it from the builder to Board, and Board plays with it.
 *   - FastBall extends it and replaces move().
 *
 * DIFFERENT FROM THE SLIDES, AND WHY
 *   Slide 59 draws a separate abstract product above the two versions. We
 *   did not add one. The original Ball already has everything a ball
 *   needs, so the Hard version only has to extend it and change move().
 *
 * WHAT CHANGED IN THIS FILE
 *   Nothing but this comment. All the code below is the original game.
 */
public class Ball extends Sprite {

    private int xdir;
    private int ydir;

    public Ball() {

        initBall();
    }

    private void initBall() {
        
        xdir = 1;
        ydir = -1;

        loadImage();
        getImageDimensions();
        resetState();
    }

    private void loadImage() {

        var ii = new ImageIcon("src/resources/ball.png");
        image = ii.getImage();
    }

    void move() {

        x += xdir;
        y += ydir;

        if (x == 0) {

            setXDir(1);
        }

        if (x == Commons.WIDTH - imageWidth) {

            System.out.println(imageWidth);
            setXDir(-1);
        }

        if (y == 0) {

            setYDir(1);
        }
    }

    private void resetState() {

        x = Commons.INIT_BALL_X;
        y = Commons.INIT_BALL_Y;
    }

    void setXDir(int x) {

        xdir = x;
    }

    void setYDir(int y) {

        ydir = y;
    }

    int getYDir() {

        return ydir;
    }
}
