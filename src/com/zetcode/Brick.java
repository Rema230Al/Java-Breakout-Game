package com.zetcode;

import java.awt.Graphics2D;

// SINGLETON CLIENT
// COMPOSITE: LEAF (slide 37: Developer)
public class Brick extends Sprite implements GameObject {

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

        ResourceManager resources = ResourceManager.getInstance();
        image = resources.getBrickImage();
    }

    boolean isDestroyed() {
        
        return destroyed;
    }

    void setDestroyed(boolean val) {

        destroyed = val;
    }

    // COMPOSITE: LEAF methods (slide 37: Developer)
    public void draw(Graphics2D g2d) {
        if (!isDestroyed()) {

            g2d.drawImage(getImage(), getX(), getY(),
                    getImageWidth(), getImageHeight(), null);
        }
    }

    public void add(GameObject gameObject) { }

    public void remove(GameObject gameObject) { }

    public GameObject getChild(int i) { return null; }
}
