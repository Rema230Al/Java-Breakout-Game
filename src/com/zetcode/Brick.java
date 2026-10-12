package com.zetcode;

/*
 * One brick. It remembers its position and whether it was destroyed.
 * Original game code, except loadImage().
 */
// [SINGLETON] CLIENT: gets its picture from the ResourceManager
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
    
    // [SINGLETON] CLIENT CODE (slide 8): get the one instance, then use it.
    // Before: var ii = new ImageIcon("src/resources/brick.png"); image = ii.getImage();
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
}
