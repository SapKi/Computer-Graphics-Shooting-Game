// Sapir Kikoz 207071192
// Shimon Cohen 315383133
package CollisionTests;

import Behaviour.Collision.BoundingBox;
import Behaviour.Collision.BoundingPoint;
import Environment.Collision.CollisionDetector;
import Model.Mathematical.Vertex;

import java.util.ArrayList;
import java.util.List;

public class BoundingBoxTests {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args) {

        List<Vertex> vertices = new ArrayList<>();
        Vertex vDownLeftClose = new Vertex(0,0,0);
        Vertex vDownRightClose = new Vertex(8,0,0);
        Vertex vDownLeftFar = new Vertex(0,0,8);
        Vertex vDownRightFar = new Vertex(8,0,8);
        Vertex vUpLeftClose = new Vertex(0,8,0);
        Vertex vUpLeftFar = new Vertex(0,8,8);
        Vertex vUpRightClose = new Vertex(8,8,0);
        Vertex vUpRightFar = new Vertex(8,8,8);

        vertices.add(vDownLeftClose);
        vertices.add(vDownRightClose);
        vertices.add(vDownLeftFar);
        vertices.add(vDownRightFar);
        vertices.add(vUpLeftClose);
        vertices.add(vUpLeftFar);
        vertices.add(vUpRightClose);
        vertices.add(vUpRightFar);
//        List<Vertex> vertexesDown = new ArrayList<>();
//        List<Vertex> vertexesUp = new ArrayList<>();
//        List<Vertex> vertexesRight = new ArrayList<>();
//        List<Vertex> vertexesLeft = new ArrayList<>();
//        List<Vertex> vertexesClose = new ArrayList<>();
//        List<Vertex> vertexesFar = new ArrayList<>();
//        vertexesDown.add(vDownLeftClose);
//        vertexesDown.add(vDownRightClose);
//        vertexesDown.add(vDownRightFar);
//        vertexesDown.add(vDownLeftFar);
//
//        vertexesUp.add(vUpLeftFar);
//        vertexesUp.add(vUpRightFar);
//        vertexesUp.add(vUpRightClose);
//        vertexesUp.add(vUpLeftClose);
//
//        vertexesRight.add(vDownRightClose);
//        vertexesRight.add(vUpRightClose);
//        vertexesRight.add(vUpRightFar);
//        vertexesRight.add(vDownRightFar);
//
//        vertexesLeft.add(vDownLeftClose);
//        vertexesLeft.add(vUpLeftClose);
//        vertexesLeft.add(vUpLeftFar);
//        vertexesLeft.add(vDownLeftFar);
//
//        vertexesClose.add(vDownLeftClose);
//        vertexesClose.add(vDownRightClose);
//        vertexesClose.add(vUpRightClose);
//        vertexesClose.add(vUpLeftClose);
//
//        vertexesFar.add(vDownLeftFar);
//        vertexesFar.add(vDownRightFar);
//        vertexesFar.add(vUpRightFar);
//        vertexesFar.add(vUpLeftFar);
//
//        Polygon polygon1 = new Polygon(vertexesClose);
//        Polygon polygon2 = new Polygon(vertexesFar);
//        Polygon polygon3 = new Polygon(vertexesDown);
//        Polygon polygon4 = new Polygon(vertexesUp);
//        Polygon polygon5 = new Polygon(vertexesLeft);
//        Polygon polygon6 = new Polygon(vertexesRight);

//        List<Polygon> polygons = new ArrayList<>();
//        polygons.add(polygon1);
//        polygons.add(polygon2);
//        polygons.add(polygon3);
//        polygons.add(polygon4);
//        polygons.add(polygon5);
//        polygons.add(polygon6);


        BoundingBox first = new BoundingBox(vertices, new Vertex(4,4,4));
        BoundingPoint point1 = new BoundingPoint(new Vertex(4, 4, 0));
        BoundingPoint point2 = new BoundingPoint(new Vertex(4, 4, -4));

        if(CollisionDetector.collide(first, point1)) {
            System.out.println(ANSI_GREEN + "Passed Test 1");
        } else {
            System.out.println(ANSI_RED + "Failed Test 1");
        }

        if(!CollisionDetector.collide(first, point2)) {
            System.out.println(ANSI_GREEN + "Passed Test 2");
        } else {
            System.out.println(ANSI_RED + "Failed Test 2");
        }
    }
}
