package com.zetcode;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// COMPOSITE: COMPOSITE (slides 38-39: Manager)
public class GameObjectGroup implements GameObject {

    List<GameObject> gameObjects = new ArrayList<GameObject>();

    public void add(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public void remove(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }

    public GameObject getChild(int i) {
        return gameObjects.get(i);
    }

    // draws every child: a leaf draws itself, a group draws its children
    public void draw(Graphics2D g2d) {

        Iterator<GameObject> gameObjectIterator = gameObjects.iterator();
        while (gameObjectIterator.hasNext()) {
            GameObject gameObject = gameObjectIterator.next();
            gameObject.draw(g2d);
        }
    }
}
