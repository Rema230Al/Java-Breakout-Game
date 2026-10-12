package com.zetcode;

/*
 * [SINGLETON]  This class is a CLIENT of the singleton.         (slides 8, 10)
 *
 * WHY IT MATTERS FOR THE PATTERN
 *   A ball needs a picture. It used to load the file itself. Now it asks
 *   the one ResourceManager for it, like every other piece of the game.
 *
 * IN THE SLIDES
 *   SingletonTest on slide 8: first get the instance, then call a method
 *   on it. Slide 10 says other classes use a singleton "by just getting
 *   it". Nobody hands the ResourceManager to the ball.
 *
 * WHO USES THE BALL
 *   - A level builder creates it in buildBall().
 *   - Level carries it from the builder to Board, and Board plays with it.
 *
 * WHAT CHANGED IN THIS FILE
 *   Only loadImage(), and the ImageIcon import it no longer needs. The
 *   rest is the original game.
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

    /*
     * [SINGLETON]  METHOD  loadImage()        (the one method we changed)
     *   Why it is here: a ball needs its picture before it can be measured
     *                   and drawn.
     *   What it does  : gets the one ResourceManager, then takes the ball
     *                   picture from it. BEFORE the refactor it loaded the
     *                   file itself:
     *                       var ii = new ImageIcon("src/resources/ball.png");
     *                       image = ii.getImage();
     *   Called by     : initBall(), which the constructor calls.
     *   Returns       : nothing (void). It fills the field "image" that
     *                   Ball inherits from Sprite.
     *   In the slides : the two lines of SingletonTest on slide 8.
     */
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
