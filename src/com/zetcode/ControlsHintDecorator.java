package com.zetcode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

// DECORATOR: CONCRETE DECORATOR (slide 51: RedShapeDecorator)
public class ControlsHintDecorator extends GameObjectDecorator {

    public ControlsHintDecorator(GameObject decoratedObject) {
        super(decoratedObject);
    }

    @Override
    public void draw(Graphics2D g2d) {
        decoratedObject.draw(g2d);
        setControlsHint(g2d);
    }

    // the added responsibility: tells the player the controls
    private void setControlsHint(Graphics2D g2d) {

        String hint = "Use LEFT / RIGHT to move";
        var font = new Font("Verdana", Font.PLAIN, 11);

        g2d.setColor(Color.DARK_GRAY);
        g2d.setFont(font);
        g2d.drawString(hint,
                (Commons.WIDTH - g2d.getFontMetrics(font).stringWidth(hint)) / 2,
                30);
    }
}
