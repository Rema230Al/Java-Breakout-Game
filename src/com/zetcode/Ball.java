package com.zetcode;

import java.awt.Graphics2D;

// SINGLETON CLIENT
// COMPOSITE: LEAF (slide 37: Developer)
public class Ball extends Sprite implements GameObject {

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

    // COMPOSITE: LEAF methods (slide 37: Developer)
    public void draw(Graphics2D g2d) {
        g2d.drawImage(getImage(), getX(), getY(),
                getImageWidth(), getImageHeight(), null);
    }

    public void add(GameObject gameObject) { }

    public void remove(GameObject gameObject) { }

    public GameObject getChild(int i) { return null; }
}
