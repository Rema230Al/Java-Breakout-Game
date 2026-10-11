package com.zetcode;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/*
 * [ABSTRACT FACTORY]  This class is a PRODUCT: the normal paddle.
 *                                                              (slides 42, 59)
 * WHY IT MATTERS FOR THE PATTERN
 *   It does two jobs:
 *   1. It is the Classic family's paddle, the one ClassicFactory creates.
 *   2. It is the TYPE the rest of the game uses for any paddle. HardFactory
 *      creates a FastPaddle but hands it out as a Paddle.
 *
 * IN THE SLIDES
 *   The common, apparent type from slide 42. It stands in for the
 *   AbstractProduct box on slide 59.
 *
 * WHO USES IT
 *   - ClassicFactory.createPaddle() creates it.
 *   - Level carries it from the builder to Board, and Board plays with it.
 *   - FastPaddle extends it and replaces move().
 *
 * DIFFERENT FROM THE SLIDES, AND WHY
 *   Slide 59 draws a separate abstract product above the two versions. We
 *   did not add one. The original Paddle already has everything a paddle
 *   needs, so the Hard version only has to extend it and change move().
 *
 * WHAT CHANGED IN THIS FILE
 *   Nothing but this comment. All the code below is the original game.
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
