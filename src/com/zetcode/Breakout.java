package com.zetcode;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.EventQueue;

/*
 * [ABSTRACT FACTORY] + [BUILDER]  This class is the CLIENT of both patterns.
 *                                                              (slides 37, 68)
 * WHY IT MATTERS FOR THE PATTERNS
 *   It is the window of the game, and it is where the game starts. It is
 *   the one place that decides which factory and which builder are used.
 *   After that, everything works through the interfaces GameFactory and
 *   LevelBuilder.
 *
 * IN THE SLIDES
 *   The Client on slide 37 (Builder) and the demo's main on slide 68
 *   (Abstract Factory).
 *
 * WHAT CHANGED IN THIS FILE
 *   initUI() used to say "add(new Board());". Now it asks two questions
 *   first. The helper method ask() is new. The rest is the original game.
 *
 * HOW TO READ THE COMMENTS IN THIS PROJECT
 *   "slide 36" means page 36 of the course PDF "Creational Design Patterns"
 *   (CPIT-251). Comments that belong to a pattern start with [BUILDER] or
 *   [ABSTRACT FACTORY].
 */
public class Breakout extends JFrame {

    public Breakout() {

        initUI();
    }

    /*
     * METHOD  initUI()
     *   Why it is here: it builds the window. It is original game code, but
     *                   its first line was replaced by the four steps below.
     *   Called by     : the constructor, when main() creates the Breakout
     *                   window.
     *   Returns       : nothing (void).
     *   Good to know  : the two answers do not depend on each other. 2
     *                   difficulties and 2 layouts give 4 different games
     *                   from 4 small classes, and a third layout would add
     *                   2 more games with only 1 new class.
     */
    private void initUI() {

        // STEP 1. Ask the player two questions. Each answer is the text of
        //         the button that was clicked, for example "Hard".
        String difficulty = ask("Choose a difficulty", new String[] {"Classic", "Hard"});
        String layout = ask("Choose a layout", new String[] {"Classic grid", "Pyramid"});

        // STEP 2. [ABSTRACT FACTORY] Turn the first answer into a factory.
        //         One factory = one whole family of objects.
        //   - FactoryProducer chooses between ClassicFactory and HardFactory,
        //     so those two names are not written here. Slide 68 does the same:
        //     AbstractFactory shapeFactory = FactoryProducer.getFactory("SHAPE");
        //   - The variable's type is the interface GameFactory, so nothing
        //     below this line knows which factory it is.
        GameFactory factory = FactoryProducer.getFactory(difficulty);

        // STEP 3. [BUILDER] Turn the second answer into a builder, and give
        //         it the factory to get its parts from.
        //   - "new" is written here for the builder, like the client on
        //     slide 37: RobotBuilder oldStyle = new OldRobotBuilder();
        //   - The variable's type is the interface again, so Board will not
        //     know which layout it gets.
        LevelBuilder builder;

        if (layout.equals("Pyramid")) {
            builder = new PyramidLevelBuilder(factory);
        } else {
            builder = new ClassicLevelBuilder(factory);
        }

        // STEP 4. Hand the builder to Board. Board uses it in gameInit(),
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
     *   Why it is here: initUI() asks two questions in the same way. This
     *                   helper holds the dialog code once, so it is not
     *                   written out twice. It is not part of either pattern.
     *   Why private   : only initUI() uses it.
     *   Takes         : the question to show, and the text for each button.
     *   What it does  : shows a dialog with one button per option and waits
     *                   for the player to click one.
     *   Called by     : initUI(), twice.
     *   Returns       : the text of the clicked button, for example "Hard".
     *                   If the player closes the dialog, the first option.
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
