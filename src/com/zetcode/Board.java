package com.zetcode;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel {

    private Timer timer;
    private String message = "Game Over";
    private Ball ball;
    private Paddle paddle;
    private Brick[] bricks;
    private boolean inGame = true;

    /*
     * [BUILDER]  FIELD  levelBuilder        (the one field we added)
     *   What it is    : the builder that will make this board's level.
     *   Why this type : LevelBuilder is the interface, so Board works the
     *                   same with the classic grid and with the pyramid. It
     *                   does not know which one it has.
     *   Why final     : set once in the constructor, never replaced.
     */
    private final LevelBuilder levelBuilder;

    /*
     * [BUILDER]  CONSTRUCTOR
     *   Takes         : the builder to use. BEFORE the refactor this
     *                   constructor took nothing: public Board().
     *   What it does  : stores the builder, then runs the normal set-up.
     *   Called by     : Breakout.initUI().
     *   Why like this : Board must not pick the layout itself, so the
     *                   builder is handed in from outside.
     *   Return type   : none. A constructor never has one.
     */
    public Board(LevelBuilder levelBuilder) {

        // IMPORTANT: this must be set BEFORE initBoard(), because
        // initBoard() -> gameInit() uses it immediately.
        this.levelBuilder = levelBuilder;

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setPreferredSize(new Dimension(Commons.WIDTH, Commons.HEIGHT));

        gameInit();
    }

    private void gameInit() {

        /*
         * [BUILDER]  METHOD  gameInit()
         * Here Board is the CLIENT that uses the director.   (slides 32, 37)
         *
         *   Why it is here: it sets up a new game. It is original game
         *                   code, but its first half was rewritten.
         *   Called by     : initBoard(), which the constructor calls.
         *   Returns       : nothing (void). It fills Board's own fields.
         *
         * BEFORE THE REFACTOR it built the level by hand:
         *
         *     bricks = new Brick[Commons.N_OF_BRICKS];
         *     ball = new Ball();
         *     paddle = new Paddle();
         *     for (int i = 0; i < 5; i++)
         *         for (int j = 0; j < 6; j++)
         *             bricks[k++] = new Brick(j * 40 + 30, i * 10 + 50);
         *
         * WHY IT IS BETTER NOW
         *   Board's job is to run the game. It no longer also decides how
         *   a level is laid out, so a new layout does not touch Board.
         */

        // 1. Create the director and give it the builder Board received.
        //    Slide 37: RobotEngineer robotEng = new RobotEngineer(oldStyle);
        var director = new LevelDirector(levelBuilder);

        // 2. Tell the director to run the build steps.
        //    Slide 37: robotEng.makeRobot();
        director.makeLevel();

        // 3. Ask the director for the finished product.
        //    Slide 37: Robot firstRobot = robotEng.getRobot();
        Level level = director.getLevel();

        // 4. Take the three parts out of the Level and keep them in Board's
        //    own fields. The game code below is the original code and uses
        //    these fields exactly as before.
        paddle = level.getPaddle();
        ball = level.getBall();
        bricks = level.getBricks();

        timer = new Timer(Commons.PERIOD, new GameCycle());
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        var g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        if (inGame) {

            drawObjects(g2d);
        } else {

            gameFinished(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics2D g2d) {

        g2d.drawImage(ball.getImage(), ball.getX(), ball.getY(),
                ball.getImageWidth(), ball.getImageHeight(), this);
        g2d.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(),
                paddle.getImageWidth(), paddle.getImageHeight(), this);

        // [BUILDER] Was "i < Commons.N_OF_BRICKS". Now the level itself
        // tells us how many bricks there are (30 classic, 20 pyramid).
        for (int i = 0; i < bricks.length; i++) {

            if (!bricks[i].isDestroyed()) {

                g2d.drawImage(bricks[i].getImage(), bricks[i].getX(),
                        bricks[i].getY(), bricks[i].getImageWidth(),
                        bricks[i].getImageHeight(), this);
            }
        }
    }

    private void gameFinished(Graphics2D g2d) {

        var font = new Font("Verdana", Font.BOLD, 18);
        FontMetrics fontMetrics = this.getFontMetrics(font);

        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString(message,
                (Commons.WIDTH - fontMetrics.stringWidth(message)) / 2,
                Commons.WIDTH / 2);
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            paddle.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {

            paddle.keyPressed(e);
        }
    }

    private class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            doGameCycle();
        }
    }

    private void doGameCycle() {

        ball.move();
        paddle.move();
        checkCollision();
        repaint();
    }

    private void stopGame() {

        inGame = false;
        timer.stop();
    }

    private void checkCollision() {

        if (ball.getRect().getMaxY() > Commons.BOTTOM_EDGE) {

            stopGame();
        }

        // [BUILDER] Victory check: was "Commons.N_OF_BRICKS" in both places.
        // With bricks.length, winning works for any layout size.
        for (int i = 0, j = 0; i < bricks.length; i++) {

            if (bricks[i].isDestroyed()) {

                j++;
            }

            if (j == bricks.length) {

                message = "Victory";
                stopGame();
            }
        }

        if ((ball.getRect()).intersects(paddle.getRect())) {

            int paddleLPos = (int) paddle.getRect().getMinX();
            int ballLPos = (int) ball.getRect().getMinX();

            int first = paddleLPos + 8;
            int second = paddleLPos + 16;
            int third = paddleLPos + 24;
            int fourth = paddleLPos + 32;

            if (ballLPos < first) {

                ball.setXDir(-1);
                ball.setYDir(-1);
            }

            if (ballLPos >= first && ballLPos < second) {

                ball.setXDir(-1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos >= second && ballLPos < third) {

                ball.setXDir(0);
                ball.setYDir(-1);
            }

            if (ballLPos >= third && ballLPos < fourth) {

                ball.setXDir(1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos > fourth) {

                ball.setXDir(1);
                ball.setYDir(-1);
            }
        }

        // [BUILDER] Was "i < Commons.N_OF_BRICKS".
        for (int i = 0; i < bricks.length; i++) {

            if ((ball.getRect()).intersects(bricks[i].getRect())) {

                int ballLeft = (int) ball.getRect().getMinX();
                int ballHeight = (int) ball.getRect().getHeight();
                int ballWidth = (int) ball.getRect().getWidth();
                int ballTop = (int) ball.getRect().getMinY();

                var pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
                var pointLeft = new Point(ballLeft - 1, ballTop);
                var pointTop = new Point(ballLeft, ballTop - 1);
                var pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

                if (!bricks[i].isDestroyed()) {

                    if (bricks[i].getRect().contains(pointRight)) {

                        ball.setXDir(-1);
                    } else if (bricks[i].getRect().contains(pointLeft)) {

                        ball.setXDir(1);
                    }

                    if (bricks[i].getRect().contains(pointTop)) {

                        ball.setYDir(1);
                    } else if (bricks[i].getRect().contains(pointBottom)) {

                        ball.setYDir(-1);
                    }

                    bricks[i].setDestroyed(true);
                }
            }
        }
    }
}
