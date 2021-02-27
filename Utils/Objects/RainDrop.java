//// Sapir Kikoz 207071192
// Shimon Cohen 315383133
package Objects;
//
//import Behaviour.Collision.AABB;
//import Behaviour.Collision.BoundingObject;
//import Behaviour.Collision.Response.CollisionResponseType;
//import Behaviour.SemiActiveHarmless;
//import Environment.CoordinateSystem.CoordinateSystem;
//import Events.EventManager;
//import Events.EventType;
//import Events.GameEvents.ObjectEvent;
//import Logic.Movement.VectoredMovement;
//import Model.Mathematical.Vector;
//
//import javax.media.opengl.GLAutoDrawable;
//import javax.media.opengl.glu.GLU;
//import java.awt.*;
//
//public class RainDrop extends SemiActiveHarmless {
//
//    private BlockSameSurface sameSurface;
//    private long timeToVanish;
//
//    /**
//     * Default constructor.
//     *
//     * @param system        the object's origin
//     */
//    public RainDrop(CoordinateSystem system) {
//        super(system, new VectoredMovement(new Vector(-1,-10,-1)), CollisionResponseType.vanish);
//        this.width = 1f;
//        this.height = 1f;
//        this.length = 1f;
//        sameSurface = new BlockSameSurface(system, Color.CYAN, width, height, length);
////        this.direction = direction;
//        timeToVanish = 70000;
//    }
//
//    @Override
//    public void draw(GLU glu, GLAutoDrawable glAutoDrawable) {
//        sameSurface.draw(glu, glAutoDrawable);
//    }
//
//    @Override
//    public ObjectKind getKind() {
//        return ObjectKind.rainDrop;
//    }
//
//    @Override
//    public void timePassed(long millisecondsPassed) {
//        super.timePassed(millisecondsPassed);
//        movementLogic.handleMovement(this, millisecondsPassed);
//        timeToVanish -= millisecondsPassed;
//        if(timeToVanish < 0) {
//            EventManager.getInstance().notify(new ObjectEvent(this, EventType.RemoveObject));
//        }
//    }
//
//    @Override
//    public BoundingObject getCurrent(long timeMilliseconds) {
//        return new AABB(getLowerBounds(), getUpperBounds());
//    }
//
////    @Override
////    public BoundingObject getNextStep() {
////        return getCurrent();
////    }
//}
