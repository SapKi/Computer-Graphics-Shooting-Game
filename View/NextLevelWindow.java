// Sapir Kikoz 207071192
// Shimon Cohen 315383133
package View;

import Events.EventManager;
import Events.GameEvents.StartGameEvent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Open a window indicating that the next level is available.
 */
public class NextLevelWindow extends Window {

    /**
     * Open the window.
     */
    public static void window() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(800, 600);
        center(frame);

        Button button = new Button("Next Level!");
        button.setBounds(400,200,60,50);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                EventManager.getInstance().notify(new StartGameEvent());
                frame.dispose();
            }
        });

        BufferedImage myImage = null;
        try {
            myImage = ImageIO.read(new File("resources/images/backgrounds/start.jpeg"));
            frame.setContentPane(new ImagePanel(myImage, frame.getWidth(), frame.getHeight()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        frame.add(button);
        button.setFocusable(false);

        frame.validate();
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.requestFocus();
    }
}
