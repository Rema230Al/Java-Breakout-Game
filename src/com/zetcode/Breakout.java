package com.zetcode;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.EventQueue;

/*
 * [BUILDER]  This class is the CLIENT.                             (slide 37)
 *
 * WHY IT MATTERS FOR THE PATTERN
 *   It is the window of the game, and it is where the game starts. It is
 *   the one place that decides which builder is used. After that,
 *   everything works through the interface LevelBuilder.
 *
 * IN THE SLIDES
 *   The Client on slide 37.
 *
 * WHAT CHANGED IN THIS FILE
 *   initUI() used to say "add(new Board());". Now it asks one question
 *   first. The helper method ask() is new. The rest is the original game.
 *
 * HOW TO READ THE COMMENTS IN THIS PROJECT
 *   "slide 36" means page 36 of the course PDF "Creational Design Patterns"
 *   (CPIT-251). Comments that belong to a pattern start with [BUILDER] or
 *   [SINGLETON].
 */
public class Breakout extends JFrame {

    public Breakout() {

        initUI();
    }

    /*
     * METHOD  initUI()
     *   Why it is here: it builds the window. It is original game code, but
     *                   its first line was replaced by the three steps below.
     *   Called by     : the constructor, when main() creates the Breakout
     *                   window.
     *   Returns       : nothing (void).
     *   Good to know  : a third layout would need one new builder class,
     *                   one more button in the question and one more "if"
     *                   here. Board and LevelDirector would stay the same.
     */
    private void initUI() {

        // STEP 1. Ask the player which layout to play. The answer is the
        //         text of the button that was clicked, for example "Pyramid".
        String layout = ask("Choose a layout", new String[] {"Classic grid", "Pyramid"});

        // STEP 2. [BUILDER] Turn the answer into a builder.
        //   - "new" is written here for the builder, like the client on
        //     slide 37: RobotBuilder oldStyle = new OldRobotBuilder();
        //   - The variable's type is the interface, so Board will not know
        //     which layout it gets.
        LevelBuilder builder;

        if (layout.equals("Pyramid")) {
            builder = new PyramidLevelBuilder();
        } else {
            builder = new ClassicLevelBuilder();
        }

        // STEP 3. Hand the builder to Board. Board uses it in gameInit(),
        //         where the director runs it (the rest of slide 37's client).
        add(new Board(builder));

        setTitle("Breakout");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
    }

    /*
     * METHOD  ask(question, options)
     *   Why it is here: it keeps the dialog code out of initUI(), so
     *                   initUI() reads as three short steps. It is not part
     *                   of either pattern.
     *   Why private   : only initUI() uses it.
     *   Takes         : the question to show, and the text for each button.
     *   What it does  : shows a dialog with one button per option and waits
     *                   for the player to click one.
     *   Called by     : initUI(), once.
     *   Returns       : the text of the clicked button, for example
     *                   "Pyramid". If the player closes the dialog, the
     *                   first option.
     */
    private String ask(String question, String[] options) {

        int choice = JOptionPane.showOptionDialog(null, question, "Breakout",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        // Closing the dialog without clicking a button returns -1.
        // In that case we use the first option.
        if (choice < 0) {
            return options[0];
        }

        return options[choice];
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var game = new Breakout();
            game.setVisible(true);
        });
    }
}
