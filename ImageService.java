package GeneralDiceGame;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Image Service.
 */
public class ImageService {
    
    public ImageService() {

    }

    /**
     * Load image.
     * @param filePath - file path
     * @return - jbutton
     */
    public static JButton loadImage(String filePath) {
        BufferedImage bufferedImage;
        JButton imageContainer;
        try {
            InputStream inputStream = ImageService.class.getResourceAsStream(filePath);
            bufferedImage = ImageIO.read(inputStream);
            Image image = bufferedImage.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            imageContainer = new JButton(new ImageIcon(image));
            return imageContainer;
        } catch (Exception e) {
            System.out.println("Error on loading image " + e);
            return null;
        }
    }

    /**
     * Update Image.
     * @param imageContainer - jbutton image container
     * @param filePath - file path
     */
    public static void updateImage(JButton imageContainer, String filePath) {
        BufferedImage bufferedImage;
        try {
            InputStream inputStream = ImageService.class.getResourceAsStream(filePath);
            bufferedImage = ImageIO.read(inputStream);
            Image image = bufferedImage.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            imageContainer.setIcon(new ImageIcon(image));
        } catch (Exception e) {
            System.out.println("Error on updating the image " + e);
        }
    } 
}
