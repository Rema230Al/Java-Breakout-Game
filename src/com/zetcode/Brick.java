package com.zetcode;

/*
 * [SINGLETON]  This class is a CLIENT of the singleton.         (slides 8, 10)
 *
 * WHY IT MATTERS FOR THE PATTERN
 *   A brick needs a picture. It used to load the file itself. Now it asks
 *   the one ResourceManager for it, like every other piece of the game.
 *
 * IN THE SLIDES
 *   SingletonTest on slide 8: first get the instance, then call a method
 *   on it. Slide 10 says other classes use a singleton "by just getting
 *   it". Nobody hands the ResourceManager to the brick.
 *
 * WHO USES THE BRICK
 *   - A level builder creates it in buildBricks(), once per brick.
 *   - Level carries it from the builder to Board, and Board plays with it.
 *
 * WHAT CHANGED IN THIS FILE
 *   Only loadImage(), and the ImageIcon import it no longer needs. The
 *   rest is the original game.
 */
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
    
    /*
     * [SINGLETON]  METHOD  loadImage()        (the one method we changed)
     *   Why it is here: a brick needs its picture before it can be measured
     *                   and drawn.
     *   What it does  : gets the one ResourceManager, then takes the brick
     *                   picture from it. BEFORE the refactor it loaded the
     *                   file itself:
     *                       var ii = new ImageIcon("src/resources/brick.png");
     *                       image = ii.getImage();
     *   Called by     : initBrick(), which the constructor calls.
     *   Returns       : nothing (void). It fills the field "image" that
     *                   Brick inherits from Sprite.
     *   In the slides : the two lines of SingletonTest on slide 8.
     */
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
