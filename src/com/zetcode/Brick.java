package com.zetcode;

import javax.swing.ImageIcon;

public class Brick extends Sprite {

    private boolean destroyed;

    public Brick(int x, int y) {
        
        initBrick(x, y);
    }
    
    private void initBrick(int x, int y) {
        
        this.x = x;
        this.y = y;
        
        destroyed = false;

        loadImage();
        getImageDimensions();
    }
    
    private void loadImage() {
        
        var ii = new ImageIcon("src/resources/brick.png");
        image = ii.getImage();        
    }

    boolean isDestroyed() {
        
        return destroyed;
    }

    void setDestroyed(boolean val) {

        destroyed = val;
    }

    /*
     * [ABSTRACT FACTORY] New method added in the refactor.
     *
     * Called by Board when the ball hits this brick. BEFORE, Board called
     * setDestroyed(true) directly, which meant every brick always broke on
     * the first hit. Now the brick itself decides what a hit does:
     *   - a normal Brick breaks right away (this method),
     *   - a StrongBrick overrides hit() and needs two hits.
     * Board doesn't need to know which kind of brick it is (polymorphism).
     */
    void hit() {

        setDestroyed(true);
    }
}
