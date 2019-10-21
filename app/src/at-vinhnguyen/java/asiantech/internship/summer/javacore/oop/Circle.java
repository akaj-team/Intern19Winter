package asiantech.internship.summer.javacore.oop;

import android.util.Log;

class Circle extends Shape {

    private static final String TAG = Circle.class.getSimpleName();
    private float r;
    private final float PI = 3.14f;

    Circle(float r) {
        this.r = r;
    }

    @Override
    float Acreage() {
        return PI * r * r;
    }

    @Override
    float Perimeter() {
        return r * 2 * PI;
    }

    @Override
    void printNameOfTheShape() {
        Log.d(TAG, "Circle:");
    }
}
