// Sapir Kikoz 207071192
// Shimon Cohen 315383133
package View;

import Controller.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Open a window indicating the end of a game.
 */
public class EndWindow extends Window {

    /**
     * Open end window.
     *
     * @param controller the main controller
     * @param win true if player won, false otherwise
     */
    public static void endWindow(Controller controller, boolean win) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set frame size and position
        int width = 800, height = 600;
        frame.setSize(width, height);
        center(frame);

        // create and position button
        int buttonWidth = 60, buttonHeight = 50;
        Button button = new Button("To Menu");
        button.setBounds(width / 2 - buttonWidth / 2, height - (int)(2.5 * buttonHeight),
                buttonWidth, buttonHeight);
        // set button click event
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // go to start window
                StartWindow.startWindow(controller);
                frame.dispose();
            }
        });

        BufferedImage myImage;
        // load wanted image
        try {
            if (win) {
                myImage = ImageIO.read(new File("resources/images/backgrounds/win.jpg"));
            } else {
                myImage = ImageIO.read(new File("resources/images/backgrounds/endgame.jpg"));
            }
            frame.setContentPane(new ImagePanel(myImage, frame.getWidth(), frame.getHeight()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // add button to frame
        frame.add(button);
        button.setFocusable(false);

        // load frame
        frame.validate();
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.requestFocus();
    }
}
