// Sapir Kikoz 207071192
// Shimon Cohen 315383133
package View;

import Controller.Controller;
import Events.*;
import javafx.scene.input.KeyCode;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Open a window with game instructions.
 */
public class InstructionsWindow extends Window {

    private Controller controller;
    private List<String> imageList;
    private int currentImage;
    private Button prev;
    private Button next;
    private Button menu;

    /**
     * Constructor.
     *
     * @param controller the main controller
     */
    public InstructionsWindow(Controller controller) {
        this.controller = controller;
    }

    /**
     * Load the instructions window.
     *
     * @param canGoToMain defines if the window can redirect to the main menu
     */
    public void instructionsWindow(boolean canGoToMain) {
        imageList = new ArrayList<>();
        imageList.add("resources/images/instructions/gameScreenInstructions.jpg");
        imageList.add("resources/images/instructions/gamePlay.jpg");
        imageList.add("resources/images/instructions/importantNotes.jpg");
        imageList.add("resources/images/instructions/keyCommands.jpg");
        imageList.add("resources/images/instructions/mouseCommands.jpg");

        JFrame frame = new JFrame();
        if(canGoToMain) {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } else {
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }

        int width = 800, height = 600;
        frame.setSize(width, height);
        center(frame);

        int buttonWidth = 70, buttonHeight = 40;
        // define menu button
        menu = new Button("Main menu");
        menu.setBounds(width / 2 - buttonWidth / 2, height - 35 - buttonHeight, buttonWidth, buttonHeight);
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // open menu window
                controller.startingWindow();
                // close current window
                frame.dispose();
            }
        });

        // define prev button
        prev = new Button("Previous");
        prev.setBounds(buttonWidth, height - 35 - buttonHeight, buttonWidth, buttonHeight);
        prev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // previous image
                prev();
                // load image
                loadImage(frame, buttonHeight);
                setFrame(frame, canGoToMain);
            }
        });
        // define next button
        next = new Button("Next");
        next.setBounds(width - buttonWidth * 2 - 18, height - 35 - buttonHeight, buttonWidth, buttonHeight);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // next image
                next();
                // load image
                loadImage(frame, buttonHeight);
                setFrame(frame, canGoToMain);
            }
        });

        // load image
        loadImage(frame, buttonHeight);

        setFrame(frame, canGoToMain);
        prev.setEnabled(false);
    }

    /**
     * Set the frames settings.
     *
     * @param frame the frame to set
     */
    private void setFrame(JFrame frame, boolean canGoToMain) {
        if(canGoToMain) {
            frame.add(menu);
            menu.setFocusable(false);
        }
        frame.add(prev);
        prev.setFocusable(false);
        frame.add(next);
        next.setFocusable(false);

        frame.validate();
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.requestFocus();
    }

    /**
     * Load next image.
     */
    private void next() {
        if(currentImage + 1 < imageList.size()) {
            currentImage++;
        }
        if(currentImage == imageList.size() - 1) {
            next.setEnabled(false);
        }
        prev.setEnabled(true);
    }

    /**
     * Load previous image.
     */
    private void prev() {
        if(currentImage - 1 >= 0) {
            currentImage--;
        }
        if(currentImage == 0) {
            prev.setEnabled(false);
        }
        next.setEnabled(true);
    }

    /**
     * Load the current image.
     *
     * @param frame the frame to change its background
     * @param buttonHeight the height of the buttons in the frame
     */
    private void loadImage(JFrame frame, int buttonHeight) {
        try {
            // read image
            BufferedImage myImage = ImageIO.read(new File(imageList.get(currentImage)));
            // set frame background
            frame.setContentPane(new ImagePanel(myImage, frame.getWidth() - 5,
                    frame.getHeight() - 35 - buttonHeight));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
