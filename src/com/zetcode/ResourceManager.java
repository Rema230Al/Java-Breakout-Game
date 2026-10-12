package com.zetcode;

import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * [SINGLETON]  This class is the SINGLETON.                (slides 5, 6, 7, 10)
 *
 * WHY IT EXISTS
 *   Every paddle, ball and brick needs a picture before it can be drawn.
 *   In the original game each object loaded its own. A classic level has 30
 *   bricks, and each of the 30 ran the loading code for itself. Paddle, Ball
 *   and Brick also each carried a copy of that code. This class is the ONE
 *   place that loads the pictures, and there is only ever one object of it.
 *
 * IN THE SLIDES
 *   The Singleton box on slide 6 and the code on slide 7: a private static
 *   field "instance", a private constructor and a public static
 *   getInstance(). We kept the same three names.
 *
 * WHY IT IS A GOOD SINGLETON CANDIDATE
 *   - Slide 5: a good candidate is "something everybody needs". Paddle, Ball
 *     and Brick all need it.
 *   - Slide 10: other classes use it "by just getting it (no need to pass
 *     it as a method argument everywhere)". Without it, the pictures would
 *     have to travel from Breakout through the builders into every
 *     constructor.
 *   - Slides 10 and 13: a singleton is "only good for classes that will
 *     never need more than one instance". A second manager would only load
 *     the same three files again.
 *
 * WHO USES IT
 *   Paddle.loadImage(), Ball.loadImage() and Brick.loadImage(). Nobody else.
 *
 * GOOD TO KNOW
 *   - Slide 5 says why this is an object and not a class full of static
 *     methods: an object "can be used as a method argument" and the class
 *     "can be extended".
 *   - Slide 12 shows two other ways to write it (eager, and synchronized).
 *     We use the lazy version from slide 7. The whole game is created on
 *     one thread (see main() in Breakout), so two threads can never be
 *     inside getInstance() at the same time.
 */
public class ResourceManager {

    /*
     * FIELD  instance
     *   What it is    : the one ResourceManager object. It is null until
     *                   somebody asks for it the first time.
     *   Why static    : it belongs to the class, not to an object. It has
     *                   to, because at the start no object exists yet that
     *                   could hold it.
     *   Why private   : nobody outside may replace it or set it to null.
     *   In the slides : "private static Singleton instance = null;" on
     *                   slide 7, "to keep track if instance was created".
     */
    private static ResourceManager instance = null;

    /*
     * FIELDS  paddleImage, ballImage, brickImage
     *   What they are : the three pictures of the game, loaded one time.
     *   Why not static: they belong to the one object. This is what makes
     *                   it an object and not a fully static class (slide 5).
     *   Why final     : set once in the constructor, never replaced.
     */
    private final Image paddleImage;
    private final Image ballImage;
    private final Image brickImage;

    /*
     * CONSTRUCTOR
     *   Why private   : this is the heart of the pattern. Slide 5: "Make the
     *                   constructor private!" No other class can write
     *                   "new ResourceManager()", so a second object can
     *                   never be created.
     *   Takes         : nothing.
     *   What it does  : loads the three picture files. They are the same
     *                   files the original Paddle, Ball and Brick loaded.
     *   Called by     : only getInstance() below, and only one time.
     *   In the slides : "private Singleton() { }" on slide 7. Ours has a
     *                   body, like "private PropertiesManager() { ... }" on
     *                   slide 9.
     *   Return type   : none. A constructor never has one.
     */
    private ResourceManager() {
        paddleImage = new ImageIcon("src/resources/paddle.png").getImage();
        ballImage = new ImageIcon("src/resources/ball.png").getImage();
        brickImage = new ImageIcon("src/resources/brick.png").getImage();
    }

    /*
     * METHOD  getInstance()
     *   Why it is here: the constructor is private, so this is the only way
     *                   to get the object. Slide 7: "The only way to create
     *                   instance by calling this method".
     *   Why static    : it must work BEFORE any object exists, so it is
     *                   called on the class: ResourceManager.getInstance().
     *   What it does  : the first call finds instance == null and creates
     *                   the object. Every later call skips the "if" and
     *                   returns that same object. Slide 7 calls this lazy
     *                   instantiation: nothing is loaded until it is needed.
     *   Called by     : loadImage() in Paddle, Ball and Brick.
     *   Returns       : the one ResourceManager. Always the same object.
     */
    public static ResourceManager getInstance() {

        if (instance == null) {
            instance = new ResourceManager();
        }

        return instance;
    }

    /*
     * THE THREE GETTERS  getPaddleImage(), getBallImage(), getBrickImage()
     *   Why they exist: this is the work the singleton does for the other
     *                   classes. On slide 8 it is "newInstance.MethodName();".
     *   Why not static: they are called on the object that getInstance()
     *                   returned, not on the class.
     *   Called by     : Paddle.loadImage(), Ball.loadImage() and
     *                   Brick.loadImage(), one getter each.
     *   Returns       : the picture the constructor loaded. All the bricks
     *                   of a level get the same Image object.
     */
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
