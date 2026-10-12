package com.zetcode;

import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Loads the three pictures of the game one time and hands them out.
 * Paddle, Ball and Brick get their picture from here, so there is only
 * ever one object of this class.
 */
// [SINGLETON] THE SINGLETON CLASS (slides 6 and 7)
public class ResourceManager {

    // [SINGLETON] THE ONE INSTANCE. It is null until someone asks for it.
    private static ResourceManager instance = null;

    // the three pictures, loaded once
    private final Image paddleImage;
    private final Image ballImage;
    private final Image brickImage;

    // [SINGLETON] PRIVATE CONSTRUCTOR: no other class can write
    // "new ResourceManager()" (slide 5)
    private ResourceManager() {
        paddleImage = new ImageIcon("src/resources/paddle.png").getImage();
        ballImage = new ImageIcon("src/resources/ball.png").getImage();
        brickImage = new ImageIcon("src/resources/brick.png").getImage();
    }

    // [SINGLETON] GLOBAL ACCESS POINT: the only way to get the object
    public static ResourceManager getInstance() {

        // [SINGLETON] THE CHECK: create it on the first call only
        // (lazy instantiation, slide 7)
        if (instance == null) {
            instance = new ResourceManager();
        }

        return instance;
    }

    // what the singleton does for the other classes: hands out a picture
    public Image getPaddleImage() {
        return paddleImage;
    }

    public Image getBallImage() {
        return ballImage;
    }

    public Image getBrickImage() {
        return brickImage;
    }
}
