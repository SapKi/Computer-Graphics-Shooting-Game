// Sapir Kikoz 207071192
// Shimon Cohen 315383133
package Objects;

import Behaviour.Collision.AABB;
import Behaviour.Collision.BoundingObject;
import Behaviour.Collision.Response.CollisionResponseType;
import Behaviour.Tangible;
import Behaviour.Visible;
import Drawing.TextureKind;
import Environment.CoordinateSystem.CoordinateSystem;
import Environment.CoordinateSystem.SimpleCoordinateSystem;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a block surface.
 */
public class BlockSurface extends Tangible {

    List<Visible> tiles;

    /**
     * Default constructor.
     *
     * @param system   the object's position
     * @param response
     */
    public BlockSurface(CoordinateSystem system, TextureKind[] kinds, String[] files, CollisionResponseType response,
                        int tileSize, int tileWidthNum, int tileHeightNum, int tileLengthNum) {
        super(system, response);
        tiles = new ArrayList<>();
        width = tileWidthNum * tileSize;
        height = tileHeightNum * tileSize;
        length = tileLengthNum * tileSize;
        double x = system.getPositionX(),
                y = system.getPositionY(),
                z = system.getPositionZ();
        for (int i = 0; i < height; i += tileSize) {
            for (int j = 0; j < width; j += tileSize) {
                for (int k = 0; k < length; k += tileSize) {
                    tiles.add(new BlockDifferentSurfaces(new SimpleCoordinateSystem(x, y, z),
                            kinds, files, tileSize, tileSize, tileSize));
                    z += tileSize;
                }
                x += tileSize;
                z = system.getPositionZ();
            }
            y += tileSize;
            x = system.getPositionX();
        }
    }

    @Override
    public void draw(GLU glu, GLAutoDrawable glAutoDrawable) {
        for(Visible visible : tiles) {
            visible.draw(glu, glAutoDrawable);
        }
    }

    @Override
    public void timePassed(long millisecondsPassed) {
        for(Visible visible : tiles) {
            visible.timePassed(millisecondsPassed);
        }
    }

    @Override
    public ObjectKind getKind() {
        return ObjectKind.wall;
    }

    @Override
    public BoundingObject getCurrent(long timeMilliseconds) {
        return new AABB(getLowerBounds(), getUpperBounds());
    }
}
