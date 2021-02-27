// Sapir Kikoz 207071192
// Shimon Cohen 315383133
package Objects;

import Behaviour.Collision.*;
import Behaviour.Collision.Response.CollisionResponseType;
import Behaviour.Tangible;
import Drawing.TextureKind;
import Environment.CoordinateSystem.CoordinateSystem;
import Environment.CoordinateSystem.SimpleCoordinateSystem;
import Events.EventManager;
import Events.EventType;
import Events.GameEvents.ObjectEvent;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

/**
 * A grass platform.
 */
public class GrassFloor extends Tangible {

    /**
     * Constructor.
     *
     * @param system the object's position
     */
    public GrassFloor(CoordinateSystem system, int width, int basicTileSize, int length) {
        super(system, CollisionResponseType.stop);
        this.width = width;
        this.length = length;
        this.height = basicTileSize;
        double x = system.getPositionX(),
                y = system.getPositionY(),
                z = system.getPositionZ();
        TextureKind[] kinds = {TextureKind.ground, TextureKind.ground, TextureKind.grass,
                TextureKind.ground, TextureKind.ground, TextureKind.ground};
        String path = "resources/images/areas/grassySunset/";
        String[] files = {path + "ground.jpg", path + "ground.jpg", path + "grass.jpg", path + "ground.jpg",
                path + "ground.jpg", path + "ground.jpg"};
        for (int i = 0; i < width; i += basicTileSize) {
            for (int j = 0; j < length; j += basicTileSize) {
//                tiles.add(new BlockDifferentSurfaces(new SimpleCoordinateSystem(x, y - (float)basicTileSize / 2, z),
//                        kinds, files, basicTileSize, basicTileSize, basicTileSize));
//                tiles.add(new BlockDifferentSurfaces(new SimpleCoordinateSystem(x, y, z),
//                        kinds, files, basicTileSize, basicTileSize, basicTileSize));
                EventManager.getInstance().notify(new ObjectEvent(new BlockDifferentSurfaces(
                        new SimpleCoordinateSystem(x, y, z), kinds, files, basicTileSize, basicTileSize,
                        basicTileSize), EventType.AddObject));
                z += basicTileSize;
            }
            x += basicTileSize;
            z = (float) system.getOriginPoint().getZ();
        }
    }

    @Override
    public void draw(GLU glu, GLAutoDrawable glAutoDrawable) {
//        for (Visible v : tiles) {
//            v.draw(glu, glAutoDrawable);
//        }
    }

    @Override
    public void timePassed(long millisecondsPassed) {
//        for(Visible visible : tiles) {
//            visible.timePassed(millisecondsPassed);
//        }
    }

    @Override
    public ObjectKind getKind() {
        return ObjectKind.floor;
    }

    @Override
    public BoundingObject getCurrent(long timeMilliseconds) {
        return new AABB(getLowerBounds(), getUpperBounds());
    }
}
