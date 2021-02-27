// Sapir Kikoz 207071192
// Shimon Cohen 315383133
package CollisionTests;

import Behaviour.Collision.BoundingCylinder;
import Behaviour.Collision.BoundingPoint;
import Behaviour.Collision.BoundingSphere;
import Environment.Collision.CollisionDetector;
import Model.Mathematical.Vertex;

public class BoundingCylinderTests {

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args) {
        BoundingCylinder cylinder = new BoundingCylinder(new Vertex(0, 0, 0), 10, -10, 10);
        BoundingPoint point1 = new BoundingPoint(new Vertex(7, 7, 7));
        BoundingPoint point2 = new BoundingPoint(new Vertex(7, -11, 7));
        BoundingSphere sphere1 = new BoundingSphere(new Vertex(15, 0, 0), 10);
        BoundingSphere sphere2 = new BoundingSphere(new Vertex(21, 0, 0), 10);

        if(CollisionDetector.collide(cylinder, point1)) {
            System.out.println(ANSI_GREEN + "Passed Test 1");
        } else {
            System.out.println(ANSI_RED + "Failed Test 1");
        }

        if(CollisionDetector.collide(cylinder, sphere1)) {
            System.out.println(ANSI_GREEN + "Passed Test 2");
        } else {
            System.out.println(ANSI_RED + "Failed Test 2");
        }

        if(CollisionDetector.collide(cylinder, sphere2)) {
            System.out.println(ANSI_RED + "Failed Test 3");
        } else {
            System.out.println(ANSI_GREEN + "Passed Test 3");
        }

        if(CollisionDetector.collide(cylinder, point2)) {
            System.out.println(ANSI_RED + "Failed Test 4");
        } else {
            System.out.println(ANSI_GREEN + "Passed Test 4");
        }
    }
}
