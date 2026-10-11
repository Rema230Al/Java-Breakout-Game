package com.zetcode;

import javax.swing.ImageIcon;

/*
 * [ABSTRACT FACTORY]  Role: PRODUCT, the brick                 (slides 42, 59)
 *
 * WHAT THIS IS
 *   The normal brick. Like Ball and Paddle, it does two jobs in the pattern:
 *   1. It is the Classic family's brick, the one ClassicFactory creates.
 *   2. It is the TYPE the rest of the game uses for any brick. HardFactory
 *      creates a StrongBrick but hands it out as a Brick.
 *
 * IN THE SLIDES
 *   The common, apparent type from slide 42, standing in for the
 *   AbstractProduct box on slide 59. See the comment in Ball.java for why
 *   we did not add a separate abstract class.
 *
 * HOW WE DID IT HERE, AND WHY
 *   This is the only original product class whose code changed: we added
 *   one method, hit(), explained at the bottom of this file.
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
     * WHAT IT DOES
     *   Board calls it when the ball touches this brick.
     *
     * WHY WE ADDED IT
     *   Before, Board called setDestroyed(true) itself, so every brick
     *   always broke on the first hit and a two-hit brick was impossible
     *   without an "if" in Board. Now the brick decides what a hit does:
     *   - a normal Brick breaks right away (this method),
     *   - a StrongBrick overrides hit() and needs two hits.
     *   Board does not need to know which kind of brick it has
     *   (polymorphism).
     */
    void hit() {

        setDestroyed(true);
    }
}
