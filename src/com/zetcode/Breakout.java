package com.zetcode;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.EventQueue;

/*
 * NOTE FOR READING THE COMMENTS IN THIS PROJECT
 *   "slide 36" means page 36 of the course PDF "Creational Design Patterns"
 *   (CPIT-251). Comments that belong to a pattern start with [BUILDER] or
 *   [ABSTRACT FACTORY].
 */
public class Breakout extends JFrame {

    public Breakout() {

        initUI();
    }

    private void initUI() {

        /*
         * [ABSTRACT FACTORY] + [BUILDER]  Where both patterns are set up
         *                                                    (slides 37, 68)
         * WHAT HAPPENS HERE
         *   The player answers two questions. Each answer picks one thing:
         *       difficulty -> which FACTORY (which kind of objects)
         *       layout     -> which BUILDER (how the bricks are arranged)
         *   From then on the game only uses the interfaces GameFactory and
         *   LevelBuilder, so Board is the same for every combination.
         *
         * IN THE SLIDES
         *   - Getting the factory is the first line of the demo on slide 68:
         *         AbstractFactory shapeFactory =
         *             FactoryProducer.getFactory("SHAPE");
         *   - Creating the builder is the first line of the Client on
         *     slide 37:
         *         RobotBuilder oldStyle = new OldRobotBuilder();
         *     The slide's client writes "new" for the builder, so we do too.
         *     The other client lines (the director) are in Board.gameInit().
         *
         * WHY IT IS DONE LIKE THIS
         *   The two choices do not depend on each other. 2 difficulties and
         *   2 layouts give 4 different games from 4 small classes, and a
         *   third layout would add 2 more games with only 1 new class.
         *
         * BEFORE THE REFACTOR this whole block was one line:
         *     add(new Board());
         */

        String difficulty = ask("Choose a difficulty", new String[] {"Classic", "Hard"});
        String layout = ask("Choose a layout", new String[] {"Classic grid", "Pyramid"});

        // [ABSTRACT FACTORY] One factory = one whole family of objects.
        // The variable's type is the interface, so nothing below this line
        // knows whether it is the Classic or the Hard factory.
        GameFactory factory = FactoryProducer.getFactory(difficulty);

        // [BUILDER] Pick the layout, and give it the factory to get its
        // parts from. Again the variable's type is the interface.
        LevelBuilder builder;

        if (layout.equals("Pyramid")) {
            builder = new PyramidLevelBuilder(factory);
        } else {
            builder = new ClassicLevelBuilder(factory);
        }

        add(new Board(builder));

        setTitle("Breakout");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
    }

    /*
     * Shows one question with a button for each option, and returns the
     * text of the button the player clicked. We ask two questions, so this
     * is a method instead of the same dialog code written out twice.
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
