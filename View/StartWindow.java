// Sapir Kikoz 207071192
// Shimon Cohen 315383133
package View;

import Controller.Controller;
import Events.*;
import Events.GameEvents.StartGameEvent;
import javafx.scene.input.KeyCode;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Open the main menu window.
 */
public class StartWindow extends Window {

    /**
     * Open main menu.
     *
     * @param controller the main controller
     */
    public static void startWindow(Controller controller) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int width = 800, height = 600;
        frame.setSize(width, height);
        center(frame);

        int buttonWidth = 70, buttonHeight = 40, buttonMarginY = buttonHeight + 30;
        Button button1 = new Button("Start Game!");
        button1.setBounds(width / 2 - buttonWidth / 2, 100, buttonWidth, buttonHeight);
        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                EventManager.getInstance().notify(new StartGameEvent());
                frame.dispose();
            }
        });

        Button button2 = new Button("Instructions");
        button2.setBounds(width / 2 - buttonWidth / 2, 100 + buttonMarginY, buttonWidth, buttonHeight);
        button2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.instructionWindow(true);
                frame.dispose();
            }
        });

        BufferedImage myImage = null;
        try {
            myImage = ImageIO.read(new File("resources/images/backgrounds/start.jpeg"));
            frame.setContentPane(new ImagePanel(myImage, frame.getWidth(), frame.getHeight() - 35));
        } catch (IOException e) {
            e.printStackTrace();
        }

        frame.add(button1);
        button1.setFocusable(false);
        frame.add(button2);
        button2.setFocusable(false);

        frame.validate();
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.requestFocus();
    }

//    @Override
//    public void notify(GameEvent event) {
//        if (event.isType() == EventType.KeyEvent) {
//            // extract event information
//            KeyCode code = ((KeyEvent) event).getKey();
//
//            if (code.getName().toLowerCase().equals("f1")) {
//                new InstructionsWindow(controller).instructionsWindow(false);
//            }
//        }
//    }
}
