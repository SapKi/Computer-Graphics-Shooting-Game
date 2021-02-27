// Sapir Kikoz 207071192
// Shimon Cohen 315383133
package View;

import Drawing.Sprite;
import Drawing.SpriteCollection;
import Environment.Areas.Area;
import Environment.LightSource;
import Events.*;
import Events.GameEvents.ActiveEvents.InformationEvent;
import Events.GameEvents.ClosingWindow;
import Events.GameEvents.TimeEvent;
import Events.MouseEventHandler;
import Objects.Player;
import com.jogamp.opengl.util.FPSAnimator;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import javax.media.opengl.glu.GLU;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import static javax.media.opengl.fixedfunc.GLLightingFunc.*;

/**
 * The program's view.
 */
public class View implements GLEventListener, Listener {

    private GLU glu;
    private Frame frame;
    public static GLCanvas canvas;
    private FPSAnimator animator;
    private SpriteCollection sprites;
    private List<Area> areas;
    private long currentTime;
    private Player player;
    private Label info;
    private int levelNumber;
    private String information;

    private boolean stopped;

    private final int width = 800, height = 600;

    /**
     * Constructor.
     */
    public View(Player player, int levelNumber) {
        this.areas = new ArrayList<>();
        this.player = player;
        this.levelNumber = levelNumber;
        information = "";
        EventManager.getInstance().addListener(EventType.KeyEvent, this);
        EventManager.getInstance().addListener(EventType.Information, this);
    }

    /**
     * Set the view's areas.
     *
     * @param areas wanted areas
     */
    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    /**
     *
     * @return the view's open gl object
     */
    public GL getGlu() {
        return canvas.getGL();
    }

    /**
     * Initialize the view.
     */
    public void initialize(int distanceToDraw) {
        currentTime = System.currentTimeMillis();

        glu = new GLU();
        frame = new Frame();
        canvas = new GLCanvas();
        animator = new FPSAnimator(60);
        sprites = new SpriteCollection(player, distanceToDraw);

        stopped = false;

        frame.setSize(width, height);
        canvas.setSize(width, height);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {
                    public void run() {
                        EventManager.getInstance().notify(new ClosingWindow());
                        animator.stop();
                        frame.dispose();
                        System.exit(0);
                    }
                }).start();
            }
        });

        canvas.addGLEventListener(this);

        MouseEventHandler mouseEventHandler = new MouseEventHandler(canvas);
        canvas.addMouseListener(mouseEventHandler);
        canvas.addMouseMotionListener(mouseEventHandler);
        animator.add(canvas);

        info = new Label();
        info.setFont(new Font("L1", Font.BOLD, 30));

        frame.add(info, BorderLayout.NORTH);
        frame.add(canvas, BorderLayout.CENTER);
        frame.validate();
        frame.setVisible(true);
        animator.start();

        // set to full screen
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        canvas.requestFocus();
    }

    /**
     * Clear the view.
     */
    public void stop() {
        sprites.clear();
        for (Area area : areas) {
            area.getObjectList().clear();
        }
        stopped = true;
    }

    /**
     * Stop the animation and close frame.
     */
    private void stopView() {
        animator.stop();
//        canvas.removeGLEventListener(this);
//        canvas.destroy();
        frame.dispose();
    }

    /**
     * Add a key listener to the GL canvas.
     *
     * @param listener the key listener to add
     */
    public void addKeyListener(KeyListener listener) {
        canvas.addKeyListener(listener);
    }

    /**
     * Adds the sprite to be displayed.
     *
     * @param s a sprite
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Removes the sprite from being displayed.
     *
     * @param s a sprite
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        final GL2 gl = glAutoDrawable.getGL().getGL2();

        gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0, glAutoDrawable.getSurfaceWidth(), 0, glAutoDrawable.getSurfaceHeight(), -1, 1);
        gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);

        gl.glShadeModel(GL2.GL_SMOOTH);              // Enable Smooth Shading
        gl.glClearColor(0.0f, 0f, 0f, 0f);    // Black TexturedBackground
        gl.glClearDepth(1.0f);                      // Depth Buffer Setup
        gl.glEnable(GL2.GL_DEPTH_TEST);              // Enables Depth Testing
        gl.glDepthFunc(GL2.GL_LEQUAL);               // The Type Of Depth Testing To Do
        // Really Nice Perspective Calculations
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
        gl.glEnable(GL2.GL_TEXTURE_2D);
        gl.glEnable(GL2.GL_COLOR_MATERIAL);
        gl.glColorMaterial(gl.GL_FRONT, gl.GL_DIFFUSE);
        gl.glColorMaterial(gl.GL_FRONT, gl.GL_SPECULAR);
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_NORMALIZE);

        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        final GL2 gl = glAutoDrawable.getGL().getGL2();

        // show game information
        info.setText(" Current Level: " + levelNumber + "       LifePoints: " + player.getHitPoints()
                + "         Information: " + information);

        List<Area> areaCopy = new ArrayList<>(areas);

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glEnable(GL2.GL_COLOR_MATERIAL);

        long time = calculateTimePassed();
        EventManager.getInstance().notify(new TimeEvent(time));

        List<Integer> availableLights = getLightNumbers();
        // draw backgrounds
        List<LightSource> lightSources = new ArrayList<>();
        for (Area area : areaCopy) {
            if (area.getBackground() != null) {
                area.getBackground().draw(glu, glAutoDrawable);
                area.getBackground().timePassed(time);
            }
            area.timePassed(time);
        }
        // get and activate light sources
        for (Area area : areaCopy) {
            lightSources.addAll(area.getLights(availableLights));
        }
        for (LightSource source : lightSources) {
            source.ActivateLight(glAutoDrawable);
        }
        // display all of the objects
        sprites.drawAll(glu, glAutoDrawable);

        // let objects know that time has passed
        sprites.timePassed(time);

        gl.glFlush();

        // if view should stop
        if (stopped) {
            stopView();
        }
    }

    /**
     *
     * @return the time that has passed
     */
    private long calculateTimePassed() {
        long now = System.currentTimeMillis();
        long diff = now - currentTime;
        currentTime = now;
        return diff;
    }

    /**
     * @return a list of all of the GL lights.
     */
    private List<Integer> getLightNumbers() {
        List<Integer> lights = new ArrayList<>();
        lights.add(GL_LIGHT0);
        lights.add(GL_LIGHT1);
        lights.add(GL_LIGHT2);
        lights.add(GL_LIGHT3);
        lights.add(GL_LIGHT4);
        lights.add(GL_LIGHT5);
        lights.add(GL_LIGHT6);
        lights.add(GL_LIGHT7);
        return lights;
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        if (i3 <= 0) {
            i3 = 1;
        }
        float h = (float) i2 / (float) i3;
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(50.0f, h, 1.0, 1000.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void notify(GameEvent event) {
        if(event.isType() == EventType.Information) {
            InformationEvent e = (InformationEvent)event;
            information = e.getInformation();
        }
    }
}
