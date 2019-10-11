package asiantech.internship.summer.javacore.oop;

import java.util.ArrayList;

public class JavaCore {

    public static void main(String[] args) {
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Rectangle(3.5f, 2.5f));
        shapes.add(new Rectangle(2.5f, 2.5f));
        shapes.add(new Circle(3.67f));
        for (Shape shape : shapes) {
            shape.printNameOfTheShape();
            System.out.println("Arceage: " + shape.Acreage());
            System.out.println("Perimeter: " + shape.Perimeter());
        }
    }
}
