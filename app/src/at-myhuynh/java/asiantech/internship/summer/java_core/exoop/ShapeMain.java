package asiantech.internship.summer.java_core.exoop;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ShapeMain {
    public static void main(String[] args) {
        Logger log = Logger.getLogger(ShapeMain.class.getName());
        Shape rectangle = new Rectangle(3, 4);
        Shape square = new Square(5);
        Shape circle = new Circle(3);

        List<Shape> shapes = new ArrayList<>();
        shapes.add(circle);
        shapes.add(square);
        shapes.add(rectangle);

        for (Shape shape : shapes) {
            log.info(shape.print() + ": " + shape.perimeter() + " - " + shape.area());
        }
    }
}
