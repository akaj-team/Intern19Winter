package asiantech.internship.summer.javacore.oop;

import android.util.Log;

class Rectangle extends Shape {

    private static final String TAG = Rectangle.class.getSimpleName();
    private float a, b;

    Rectangle(float a, float b) {
        this.a = a;
        this.b = b;
    }

    @Override
    float Acreage() {
        return a * b;
    }

    @Override
    float Perimeter() {
        return (a + b) * 2;
    }

    @Override
    void printNameOfTheShape() {
        if (a == b) {
            super.printNameOfTheShape();
            return;
        }
        Log.d(TAG, "Rectangle:");
    }
}
