package com.zetcode;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.EventQueue;

/*
 * The game window, and where the game starts. It asks which layout to
 * play, creates the matching builder and hands it to Board.
 * In the comments, "slide 36" means page 36 of the Creational slides.
 */
// [BUILDER] CLIENT: the only class that picks a concrete builder (slide 37)
public class Breakout extends JFrame {

    public Breakout() {

        initUI();
    }

    private void initUI() {

        String layout = ask("Choose a layout", new String[] {"Classic grid", "Pyramid"});

        // [BUILDER] the answer becomes a CONCRETE BUILDER, kept as the
        // interface type so nobody after this line knows which one it is
        LevelBuilder builder;

        if (layout.equals("Pyramid")) {
            builder = new PyramidLevelBuilder();
        } else {
            builder = new ClassicLevelBuilder();
        }

        // [BUILDER] the builder is handed to Board (was: add(new Board());)
        add(new Board(builder));

        setTitle("Breakout");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
    }

    // helper, not part of a pattern: shows a dialog with one button per
    // option and returns the text of the clicked button
    private String ask(String question, String[] options) {

        int choice = JOptionPane.showOptionDialog(null, question, "Breakout",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        // closing the dialog returns -1: use the first option
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
