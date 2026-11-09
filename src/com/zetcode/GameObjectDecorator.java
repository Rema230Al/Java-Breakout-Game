package com.zetcode;

import java.awt.Graphics2D;

// DECORATOR: DECORATOR (slide 50: ShapeDecorator)
public abstract class GameObjectDecorator implements GameObject {

    protected GameObject decoratedObject;

    public GameObjectDecorator(GameObject decoratedObject) {
        this.decoratedObject = decoratedObject;
    }

    public void draw(Graphics2D g2d) {
        decoratedObject.draw(g2d);
    }

    // passed on to the wrapped object, like draw()
    public void add(GameObject gameObject) {
        decoratedObject.add(gameObject);
    }

    public void remove(GameObject gameObject) {
        decoratedObject.remove(gameObject);
    }

    public GameObject getChild(int i) {
        return decoratedObject.getChild(i);
    }
}
