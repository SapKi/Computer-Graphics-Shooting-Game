// Sapir Kikoz 207071192
// Shimon Cohen 315383133
package Objects;

import Behaviour.Alive;
import Behaviour.Collision.*;
import Behaviour.Collision.Response.*;
import Environment.CoordinateSystem.ComplexCoordinateSystem;
import Environment.CoordinateSystem.CoordinateSystem;
import Environment.Dimension;
import Environment.CoordinateSystem.SimpleCoordinateSystem;
import Events.*;
import Events.GameEvents.StopEvent;
import Events.MouseEvents.RotationEvent;
import Logic.KeyMovementLogic;
import Model.Mathematical.Vector;
import Model.Mathematical.Vertex;
import Environment.Sound.SoundManager;
import Environment.Sound.SoundType;
import javafx.scene.input.KeyCode;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

/**
 * The player object.
 */
public class Player extends Alive implements Listener {

    /**
     * Constructor.
     *
     * @param movementLogic the logic to move the player by
     * @param x             the player's x position
     * @param y             the player's y position
     * @param z             the player's z position
     */
    public Player(KeyMovementLogic movementLogic, double x, double y, double z, double hitPoints) {
        super(new ComplexCoordinateSystem(new SimpleCoordinateSystem(x, y, z)),
                movementLogic, new Vector(0, 0, 0), new Shock(new Vector(0, 0, 5)), hitPoints);

        width = 1;
        height = 1;
        length = 1;
    }

    /**
     * Sign up to get notified when wanted events happen.
     */
    public void signUpForEvents() {
        EventManager.getInstance().addListener(EventType.KeyEvent, this);
        EventManager.getInstance().addListener(EventType.StopEvent, this);
        EventManager.getInstance().addListener(EventType.Rotation, this);
    }

    @Override
    public void draw(GLU glu, GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public Vertex getCenter() {
        return new Vertex(system.getPositionX(), system.getPositionY(), system.getPositionZ());
    }

    @Override
    public ObjectKind getKind() {
        return ObjectKind.player;
    }

    @Override
    public void notify(GameEvent event) {
        if (event.isType() == EventType.KeyEvent) {
            KeyCode code = ((KeyEvent) event).getKey();

            if (code.getName().toLowerCase().equals("space")) {
                shoot();
            } else {
                ((KeyMovementLogic) movementLogic).handleMovement(this, code);
            }
        } else if (event.isType() == EventType.StopEvent) {
            velocity = new Vector();

            KeyCode code = ((StopEvent) event).getKey();
            if (code.getName().toLowerCase().equals("space")) {
                SoundManager.getInstance().stopClip(SoundType.shooting);
            } else if (code.getName().toLowerCase().equals("a")
                    || code.getName().toLowerCase().equals("w")
                    || code.getName().toLowerCase().equals("s")
                    || code.getName().toLowerCase().equals("d")) {
                SoundManager.getInstance().stopClip(SoundType.movement);
            }
        } else if (event.isType() == EventType.Rotation) {
            RotationEvent e = ((RotationEvent) event);
            rotate(Dimension.Y, e.getXAngle());
        }
    }

    private void shoot() {
        weaponLogic.shoot();
    }

    @Override
    public BoundingObject getCurrent(long timeMilliseconds) {
        CoordinateSystem tempSystem = new ComplexCoordinateSystem((ComplexCoordinateSystem) system);
        tempSystem.move(velocity.mult((float) timeMilliseconds / 1000));
        Vertex center = tempSystem.getOriginPoint();
        Vertex min = new Vertex(center.getX() - 1, center.getY() - 5, center.getZ() - 1);
        Vertex max = new Vertex(center.getX() + 1, center.getY(), center.getZ() + 1);
        return new AABB(min, max);
    }

    public BoundingObject getBottom(long timeMilliseconds) {
        CoordinateSystem tempSystem = new ComplexCoordinateSystem((ComplexCoordinateSystem) system);
        tempSystem.move(velocity.plus(new Vector(0, -1, 0)).mult((float) timeMilliseconds / 1000));
//        tempSystem.move(new Vector(0, -1, 0));
        Vertex center = tempSystem.getOriginPoint();
        Vertex min = new Vertex(center.getX() - 1, center.getY() - 5, center.getZ() - 1);
        Vertex max = new Vertex(center.getX() + 1, center.getY(), center.getZ() + 1);
        return new AABB(min, max);
    }
}
