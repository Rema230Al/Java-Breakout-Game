package com.zetcode;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/*
 * [ABSTRACT FACTORY]  Role: PRODUCT, the paddle                (slides 42, 59)
 *
 * WHAT THIS IS
 *   The normal paddle. Like Ball, it does two jobs in the pattern:
 *   1. It is the Classic family's paddle, the one ClassicFactory creates.
 *   2. It is the TYPE the rest of the game uses for any paddle. HardFactory
 *      creates a FastPaddle but hands it out as a Paddle.
 *
 * IN THE SLIDES
 *   The common, apparent type from slide 42, standing in for the
 *   AbstractProduct box on slide 59. See the comment in Ball.java for why
 *   we did not add a separate abstract class.
 *
 * HOW WE DID IT HERE, AND WHY
 *   The code of this original class did not change; only this comment was
 *   added.
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
    
    private void loadImage() {
        
        var ii = new ImageIcon("src/resources/paddle.png");
        image = ii.getImage();        
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
