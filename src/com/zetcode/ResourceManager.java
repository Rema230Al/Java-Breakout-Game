package com.zetcode;

import java.awt.Image;
import javax.swing.ImageIcon;

// THE SINGLETON  
public class ResourceManager {

   
    private static ResourceManager instance = null;

    private final Image paddleImage;
    private final Image ballImage;
    private final Image brickImage;

    


    private ResourceManager() {
        paddleImage = new ImageIcon("src/resources/paddle.png").getImage();
        ballImage = new ImageIcon("src/resources/ball.png").getImage();
        brickImage = new ImageIcon("src/resources/brick.png").getImage();
    }

    
    

    public static ResourceManager getInstance() {

        if (instance == null) {
            instance = new ResourceManager();
        }

        return instance;
    }

    public Image getPaddleImage() {
        return paddleImage;
    }

    public Image getBallImage() {
        return ballImage;
    }

    public Image getBrickImage() {
        return brickImage;
    }
}
