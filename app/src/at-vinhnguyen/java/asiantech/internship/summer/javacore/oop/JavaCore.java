package asiantech.internship.summer.javacore.oop;

import android.util.Log;
import java.util.ArrayList;

public class JavaCore {

    private static final String TAG = JavaCore.class.getSimpleName();

    public static void main(String[] args) {
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Rectangle(3.5f, 2.5f));
        shapes.add(new Rectangle(2.5f, 2.5f));
        shapes.add(new Circle(3.67f));
        for (Shape shape : shapes) {
            shape.printNameOfTheShape();
            Log.d(TAG, "Arceage: " + shape.Acreage());
            Log.d(TAG, "Perimeter: " + shape.Perimeter());
        }
    }
}
