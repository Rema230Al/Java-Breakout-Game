package com.zetcode;

import javax.swing.ImageIcon;

/*
 * [ABSTRACT FACTORY]  This class is a PRODUCT: the normal brick.
 *                                                              (slides 42, 59)
 * WHY IT MATTERS FOR THE PATTERN
 *   It does two jobs:
 *   1. It is the Classic family's brick, the one ClassicFactory creates.
 *   2. It is the TYPE the rest of the game uses for any brick. HardFactory
 *      creates a StrongBrick but hands it out as a Brick.
 *
 * IN THE SLIDES
 *   The common, apparent type from slide 42. It stands in for the
 *   AbstractProduct box on slide 59.
 *
 * WHO USES IT
 *   - ClassicFactory.createBrick() creates it.
 *   - The level builders collect the bricks in an array, Level carries the
 *     array to Board, and Board plays with them.
 *   - StrongBrick extends it and replaces hit().
 *
 * DIFFERENT FROM THE SLIDES, AND WHY
 *   Slide 59 draws a separate abstract product above the two versions. We
 *   did not add one. The original Brick already has everything a brick
 *   needs, so the Hard version only has to extend it and change hit().
 *
 * WHAT CHANGED IN THIS FILE
 *   One method was added, hit(), at the bottom. It is the only code we
 *   added to an original product class. The rest is the original game.
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
     * [ABSTRACT FACTORY]  METHOD  hit()        (the one method we added)
     *   Why it is here: so that the BRICK decides what a hit means, not
     *                   Board. Before, Board called setDestroyed(true)
     *                   itself, so every brick always broke on the first
     *                   hit, and a two-hit brick was impossible without an
     *                   "if" in Board.
     *   What it does  : a normal brick breaks right away.
     *   Called by     : Board.checkCollision(), each time the ball touches
     *                   a brick that is not destroyed yet.
     *   Returns       : nothing (void).
     *   Overridden by : StrongBrick, which needs two hits. Board calls
     *                   hit() the same way for both kinds of brick. That is
     *                   polymorphism.
     *   Why no public : the other methods of this class have none either.
     */
    void hit() {

        setDestroyed(true);
    }
}
