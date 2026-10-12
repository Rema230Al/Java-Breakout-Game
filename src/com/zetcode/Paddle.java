package com.zetcode;

import java.awt.event.KeyEvent;

/*
 * [SINGLETON]  This class is a CLIENT of the singleton.         (slides 8, 10)
 *
 * WHY IT MATTERS FOR THE PATTERN
 *   A paddle needs a picture. It used to load the file itself. Now it asks
 *   the one ResourceManager for it, like every other piece of the game.
 *
 * IN THE SLIDES
 *   SingletonTest on slide 8: first get the instance, then call a method
 *   on it. Slide 10 says other classes use a singleton "by just getting
 *   it". Nobody hands the ResourceManager to the paddle.
 *
 * WHO USES THE PADDLE
 *   - A level builder creates it in buildPaddle().
 *   - Level carries it from the builder to Board, and Board plays with it.
 *
 * WHAT CHANGED IN THIS FILE
 *   Only loadImage(), and the ImageIcon import it no longer needs. The
 *   rest is the original game.
 */
public class Paddle extends Sprite  {

    private int dx;

    public Paddle() {
        
        initPaddle();        
    }
    
    private void initPaddle() {

        loadImage();
        getImageDimensions();

        resetState();
    }
    
    /*
     * [SINGLETON]  METHOD  loadImage()        (the one method we changed)
     *   Why it is here: a paddle needs its picture before it can be measured
     *                   and drawn.
     *   What it does  : gets the one ResourceManager, then takes the paddle
     *                   picture from it. BEFORE the refactor it loaded the
     *                   file itself:
     *                       var ii = new ImageIcon("src/resources/paddle.png");
     *                       image = ii.getImage();
     *   Called by     : initPaddle(), which the constructor calls.
     *   Returns       : nothing (void). It fills the field "image" that
     *                   Paddle inherits from Sprite.
     *   In the slides : the two lines of SingletonTest on slide 8.
     */
    private void loadImage() {

        ResourceManager resources = ResourceManager.getInstance();
        image = resources.getPaddleImage();
    }

    void move() {

        x += dx;

        if (x <= 0) {

            x = 0;
        }

        if (x >= Commons.WIDTH - imageWidth) {

            x = Commons.WIDTH - imageWidth;
        }
    }

    void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 1;
        }
    }

    void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 0;
        }
    }

    private void resetState() {

        x = Commons.INIT_PADDLE_X;
        y = Commons.INIT_PADDLE_Y;
    }
}
