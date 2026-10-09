package com.zetcode;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.EventQueue;

public class Breakout extends JFrame {

    public Breakout() {
        
        initUI();
    }
    
    private void initUI() {

        /*
         * [ABSTRACT FACTORY] + [BUILDER] Breakout is the CLIENT of both
         * patterns. It is the ONLY place where concrete classes are chosen:
         *   - which FACTORY  -> which kind of objects (Classic or Hard)
         *   - which BUILDER  -> which layout (classic grid or pyramid)
         * Everything after this point works only with the interfaces
         * (GameFactory, LevelBuilder), so Board never changes.
         *
         * BEFORE this was just: add(new Board());
         */

        // Ask the player for a difficulty. Closing the dialog returns -1,
        // which falls through to Classic.
        String[] modes = {"Classic", "Hard"};
        int choice = JOptionPane.showOptionDialog(null,
                "Choose a difficulty", "Breakout",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, modes, modes[0]);

        // [ABSTRACT FACTORY] Pick ONE factory = pick the whole family.
        GameFactory factory = (choice == 1) ? new HardFactory() : new ClassicFactory();

        // [BUILDER] Pick the layout and give it the factory to get its parts from.
        add(new Board(new ClassicLevelBuilder(factory)));
        // To demo the second layout, swap the line above for:
        // add(new Board(new PyramidLevelBuilder(factory)));

        setTitle("Breakout");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {

            var game = new Breakout();
            game.setVisible(true);
        });
    }
}
