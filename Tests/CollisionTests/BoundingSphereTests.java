// Sapir Kikoz 207071192
// Shimon Cohen 315383133
package CollisionTests;

import Behaviour.Collision.BoundingPoint;
import Behaviour.Collision.BoundingSphere;
import Environment.Collision.CollisionDetector;
import Model.Mathematical.Vertex;

public class BoundingSphereTests {

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args) {
        BoundingSphere first = new BoundingSphere(new Vertex(0,0,0), 10);
        BoundingSphere second = new BoundingSphere(new Vertex(7,0,0), 10);
        BoundingPoint point1 = new BoundingPoint(new Vertex(7, -6, 3));
        BoundingPoint point2 = new BoundingPoint(new Vertex(20, 0, 0));

        if(CollisionDetector.collide(first, second)) {
            System.out.println(ANSI_GREEN + "Passed Test 1");
        } else {
            System.out.println(ANSI_RED + "Failed Test 1");
        }

        if(CollisionDetector.collide(first, point1)) {
            System.out.println(ANSI_GREEN + "Passed Test 2");
        } else {
            System.out.println(ANSI_RED + "Failed Test 2");
        }

        if(CollisionDetector.collide(point1, second)) {
            System.out.println(ANSI_GREEN + "Passed Test 3");
        } else {
            System.out.println(ANSI_RED + "Failed Test 3");
        }

        if(CollisionDetector.collide(first, point2)) {
            System.out.println(ANSI_RED + "Failed Test 2");
        } else {
            System.out.println(ANSI_GREEN + "Passed Test 2");
        }

        if(CollisionDetector.collide(point2, second)) {
            System.out.println(ANSI_RED + "Failed Test 3");
        } else {
            System.out.println(ANSI_GREEN + "Passed Test 3");
        }
    }
}
