// Sapir Kikoz 207071192
// Shimon Cohen 315383133
package Objects;

import Behaviour.BasicGameObject;
import Behaviour.ObjectType;
import Logic.CameraPositionLogic;
import Model.Game;
import Drawing.Sprite;
import Environment.CoordinateSystem.ComplexCoordinateSystem;
import Environment.CoordinateSystem.SimpleCoordinateSystem;
import Events.*;
import Model.Mathematical.Vector;
import Model.Mathematical.Vertex;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

/**
 * The game's camera.
 * Follows the player.
 */
public class Camera extends BasicGameObject implements Listener, Sprite {

    private CameraPositionLogic logic;

    /**
     * Constructor.
     * @param logic the logic to move by
     * @param x the camera's x position
     * @param y the camera's y position
     * @param z the camera's z position
     */
    public Camera(CameraPositionLogic logic, int x, int y, int z) {
        super(new ComplexCoordinateSystem(new SimpleCoordinateSystem(x, y, z)));
        this.logic = logic;

        width = 0;
        height = 0;
        length = 0;

        EventManager.getInstance().addListener(EventType.KeyEvent, this);
    }

    /**
     * Constructor.
     * @param logic the logic to move by
     * @param system the camera's position
     */
    public Camera(CameraPositionLogic logic, SimpleCoordinateSystem system) {
        super(new ComplexCoordinateSystem(system));
        this.logic = logic;

        width = 0;
        height = 0;
        length = 0;

        EventManager.getInstance().addListener(EventType.KeyEvent, this);
    }

    @Override
    public void notify(GameEvent event) {

    }

    @Override
    public void draw(GLU glu, GLAutoDrawable glAutoDrawable) {
        // set the camera's position according to the position logic
        system.setPositionX(logic.getPosition().getX());
        system.setPositionY(logic.getPosition().getY());
        system.setPositionZ(logic.getPosition().getZ());

        // get the camera's lookAt point from the position logic
        Vertex lookAt = logic.getLookAt();

        // get the camera's up vector from the position logic
        Vector up = logic.getUpVector();

        // change the camera's lookAt state
        glu.gluLookAt(system.getPositionX(), system.getPositionY(), system.getPositionZ(),
                lookAt.getX(), lookAt.getY(), lookAt.getZ(),
                up.getX(), up.getY(), up.getZ());
    }

    @Override
    public void timePassed(long millisecondsPassed) {

    }

    @Override
    public void addToGame(Game game) {
        game.addSprite(this);
    }

    @Override
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }

    @Override
    public ObjectKind getKind() {
        return ObjectKind.camera;
    }

    @Override
    public boolean isType(ObjectType type) {
        return type == ObjectType.Invisible;
    }

    /**
     * Set the camera's logic to be the given logic.
     * @param logic the new position logic to act by
     */
    public void setLogic(CameraPositionLogic logic) {
        this.logic = logic;
    }
}