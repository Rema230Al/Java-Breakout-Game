package com.zetcode;

import java.awt.event.KeyEvent;

/*
 * The paddle. The player moves it with the left and right arrow keys.
 * Original game code, except loadImage().
 */
// [SINGLETON] CLIENT: gets its picture from the ResourceManager
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
    
    // [SINGLETON] CLIENT CODE (slide 8): get the one instance, then use it.
    // Before: var ii = new ImageIcon("src/resources/paddle.png"); image = ii.getImage();
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
