// Sapir Kikoz 207071192
// Shimon Cohen 315383133
package utils;

import Behaviour.Collision.Polygon;
import Model.Mathematical.Vertex;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates boxes.
 */
public class BoxCreator {

    /**
     * Creates a list of polygons representing a box.
     *
     * @param vertices the box's vertices
     * @return a list of polygons representing a box
     */
    public static List<Polygon> createPolygons(List<Vertex> vertices) {
        List<Polygon> polygons = new ArrayList<>();
        Vertex min = getMinVertex(vertices);
        Vertex max = getMaxVertex(vertices);

        // back
        List<Vertex> polyVertexes = new ArrayList<>();
        polyVertexes.add(new Vertex(min));
        polyVertexes.add(new Vertex(max.getX(), min.getY(), min.getZ()));
        polyVertexes.add(new Vertex(max.getX(), max.getY(), min.getZ()));
        polyVertexes.add(new Vertex(min.getX(), max.getY(), min.getZ()));
        polygons.add(new Polygon(polyVertexes));

        // front
        polyVertexes = new ArrayList<>();
        polyVertexes.add(new Vertex(max));
        polyVertexes.add(new Vertex(min.getX(), max.getY(), max.getZ()));
        polyVertexes.add(new Vertex(min.getX(), min.getY(), max.getZ()));
        polyVertexes.add(new Vertex(max.getX(), min.getY(), max.getZ()));
        polygons.add(new Polygon(polyVertexes));

        // top
        polyVertexes = new ArrayList<>();
        polyVertexes.add(new Vertex(max));
        polyVertexes.add(new Vertex(max.getX(), max.getY(), min.getZ()));
        polyVertexes.add(new Vertex(min.getX(), max.getY(), min.getZ()));
        polyVertexes.add(new Vertex(min.getX(), max.getY(), max.getZ()));
        polygons.add(new Polygon(polyVertexes));

        // bottom
        polyVertexes = new ArrayList<>();
        polyVertexes.add(new Vertex(min));
        polyVertexes.add(new Vertex(min.getX(), min.getY(), max.getZ()));
        polyVertexes.add(new Vertex(max.getX(), min.getY(), max.getZ()));
        polyVertexes.add(new Vertex(max.getX(), min.getY(), min.getZ()));
        polygons.add(new Polygon(polyVertexes));

        // left
        polyVertexes = new ArrayList<>();
        polyVertexes.add(new Vertex(min));
        polyVertexes.add(new Vertex(min.getX(), min.getY(), max.getZ()));
        polyVertexes.add(new Vertex(min.getX(), max.getY(), max.getZ()));
        polyVertexes.add(new Vertex(min.getX(), max.getY(), min.getZ()));
        polygons.add(new Polygon(polyVertexes));

        // right
        polyVertexes = new ArrayList<>();
        polyVertexes.add(new Vertex(max));
        polyVertexes.add(new Vertex(max.getX(), max.getY(), min.getZ()));
        polyVertexes.add(new Vertex(max.getX(), min.getY(), min.getZ()));
        polyVertexes.add(new Vertex(max.getX(), min.getY(), max.getZ()));
        polygons.add(new Polygon(polyVertexes));

        return polygons;
    }

    /**
     * Returns maximum vertex.
     *
     * @param vertices all vertices
     * @return maximum vertex
     */
    public static Vertex getMaxVertex(List<Vertex> vertices){
        Vertex v = vertices.get(0);
        double maxX = v.getX(), maxY = v.getY(), maxZ = v.getZ();
        for(Vertex ve : vertices) {
            if (maxX < ve.getX()) {
                maxX = ve.getX();
            }
            if (maxY < ve.getY()) {
                maxY = ve.getY();
            }
            if (maxZ < ve.getZ()) {
                maxZ = ve.getZ();
            }
        }
        return new Vertex(maxX, maxY, maxZ);
    }

    /**
     * Returns minimum vertex.
     *
     * @param vertices all vertices
     * @return minimum vertex
     */
    public static Vertex getMinVertex(List<Vertex> vertices){
        Vertex v = vertices.get(0);
        double minX = v.getX(), minY = v.getY(), minZ = v.getZ();
        for(Vertex ve : vertices) {
            if (minX > ve.getX()) {
                minX = ve.getX();
            }
            if (minY > ve.getY()) {
                minY = ve.getY();
            }
            if (minZ > ve.getZ()) {
                minZ = ve.getZ();
            }
        }
        return new Vertex(minX, minY, minZ);
    }
}
