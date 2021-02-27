// Sapir Kikoz 207071192
// Shimon Cohen 315383133
package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Represents a basic window.
 */
public abstract class Window {

    /**
     * Set frame to center of screen.
     *
     * @param frame frame to set
     */
    protected static void center(JFrame frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    /**
     * Set a given frame's icon.
     *
     * @param frame the frame to set
     * @param icon the wanted icon
     */
    static void setIcon(Frame frame, String icon) {
        BufferedImage myImage = null;
        try {
            myImage = ImageIO.read(new File(icon));
            frame.setIconImage(myImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * An image panel class.
     */
    static class ImagePanel extends JComponent {

        private Image image;
        private int width;
        private int height;

        /**
         * Constructor.
         *
         * @param image wanted image
         * @param width image width
         * @param height image height
         */
        ImagePanel(Image image, int width, int height) {
            this.image = image;
            this.width = width;
            this.height = height;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, width, height,this);
        }
    }
}
