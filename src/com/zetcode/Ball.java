package com.zetcode;

/*
 * The ball. It moves one pixel per tick and bounces off the side and top
 * walls.
 * Original game code, except loadImage().
 */
// [SINGLETON] CLIENT: gets its picture from the ResourceManager
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

    // [SINGLETON] CLIENT CODE (slide 8): get the one instance, then use it.
    // Before: var ii = new ImageIcon("src/resources/ball.png"); image = ii.getImage();
    private void loadImage() {

        ResourceManager resources = ResourceManager.getInstance();
        image = resources.getBallImage();
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
