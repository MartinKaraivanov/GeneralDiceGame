package GeneralDiceGame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.*;

class BackgroundPanel extends JPanel {
    private BufferedImage backgroundImage;

    /**
     * Constructor.
     * @param imagePath - image path
     */
    public BackgroundPanel(String imagePath) {
        InputStream inputStream = BackgroundPanel.class.getResourceAsStream(imagePath);
        try {
            backgroundImage = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

