package com.zetcode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

// DECORATOR: CONCRETE DECORATOR (slide 51: RedShapeDecorator)
public class MessageDecorator extends GameObjectDecorator {

    private String message;

    public MessageDecorator(GameObject decoratedObject, String message) {
        super(decoratedObject);
        this.message = message;
    }

    @Override
    public void draw(Graphics2D g2d) {
        decoratedObject.draw(g2d);
        drawMessage(g2d);
    }

    // DECORATOR: ADDED BEHAVIOR (slide 51: setRedBorder)
    private void drawMessage(Graphics2D g2d) {

        var font = new Font("Verdana", Font.PLAIN, 11);

        g2d.setColor(Color.DARK_GRAY);
        g2d.setFont(font);
        g2d.drawString(message,
                (Commons.WIDTH - g2d.getFontMetrics(font).stringWidth(message)) / 2,
                30);
    }
}
