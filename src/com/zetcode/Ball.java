package com.zetcode;

import javax.swing.ImageIcon;

/*
 * [ABSTRACT FACTORY]  Role: PRODUCT, the ball                  (slides 42, 59)
 *
 * WHAT THIS IS
 *   The normal ball. In the pattern it does two jobs:
 *   1. It is the Classic family's ball, the one ClassicFactory creates.
 *   2. It is the TYPE the rest of the game uses for any ball. HardFactory
 *      creates a FastBall but hands it out as a Ball.
 *
 * IN THE SLIDES
 *   - Slide 59 draws a separate AbstractProduct with two subclasses next to
 *     each other (ProductB1, ProductB2).
 *   - Slide 42 says products have a "common ancestor, same apparent type,
 *     different actual type". Ball is that common, apparent type.
 *
 * HOW WE DID IT HERE, AND WHY
 *   We did not add a separate abstract ball above this class. The original
 *   Ball already has everything a ball needs, so the Hard version only has
 *   to extend it and change move(). This way the code of this original
 *   class did not change at all; only this comment was added.
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
