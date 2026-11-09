package com.zetcode;

import java.awt.Graphics2D;

// COMPOSITE: COMPONENT (slide 36: Employee)
// DECORATOR: COMPONENT (slide 49: Shape)
public interface GameObject {

    public void draw(Graphics2D g2d);

    public void add(GameObject gameObject);

    public void remove(GameObject gameObject);

    public GameObject getChild(int i);
}
