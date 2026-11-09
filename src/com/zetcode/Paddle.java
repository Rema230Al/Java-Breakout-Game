package com.zetcode;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

// SINGLETON CLIENT
// COMPOSITE: LEAF (slide 37: Developer)
public class Paddle extends Sprite implements GameObject {

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

    // COMPOSITE: LEAF methods (slide 37: Developer)
    public void draw(Graphics2D g2d) {
        g2d.drawImage(getImage(), getX(), getY(),
                getImageWidth(), getImageHeight(), null);
    }

    public void add(GameObject gameObject) { }

    public void remove(GameObject gameObject) { }

    public GameObject getChild(int i) { return null; }
}
