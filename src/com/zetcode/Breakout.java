package com.zetcode;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.EventQueue;


public class Breakout extends JFrame {

    public Breakout() {

        initUI();
    }

    private void initUI() {

        String layout = ask("Choose a layout", new String[] {"Classic grid", "Pyramid"});

        //CONCRETE BUILDER is chosen here
        LevelBuilder builder;

        if (layout.equals("Pyramid")) {
            builder = new PyramidLevelBuilder();
        } else {
            builder = new ClassicLevelBuilder();
        }

        // builder handed to Board
        add(new Board(builder));

        setTitle("Breakout");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
    }

    
    private String ask(String question, String[] options) {

        int choice = JOptionPane.showOptionDialog(null, question, "Breakout",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        
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
