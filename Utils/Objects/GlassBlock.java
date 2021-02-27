// Sapir Kikoz 207071192
// Shimon Cohen 315383133
package Objects;

import Behaviour.Collision.AABB;
import Behaviour.Collision.BoundingObject;
import Behaviour.Collision.Response.CollisionResponseType;
import Behaviour.Tangible;
import Environment.CoordinateSystem.CoordinateSystem;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

/**
 * A glass block.
 * Can see through it but not go through it.
 */
public class GlassBlock extends Tangible {

    /**
     * Constructor.
     *
     * @param system the object's position
     * @param response response type
     * @param blockSize wanted block size
     */
    public GlassBlock(CoordinateSystem system, CollisionResponseType response, int blockSize) {
        super(system, response);
        width = blockSize;
        height = blockSize;
        length = blockSize;
    }

    @Override
    public void draw(GLU glu, GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void timePassed(long millisecondsPassed) {

    }

    @Override
    public ObjectKind getKind() {
        return ObjectKind.glass;
    }

    @Override
    public BoundingObject getCurrent(long timeMilliseconds) {
        return new AABB(getLowerBounds(), getUpperBounds());
    }
}
