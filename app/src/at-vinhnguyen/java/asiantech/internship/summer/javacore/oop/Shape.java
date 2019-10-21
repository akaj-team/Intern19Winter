package asiantech.internship.summer.javacore.oop;

import android.util.Log;

abstract class Shape {

    private static final String TAG = Shape.class.getSimpleName();

    void printNameOfTheShape() {
        Log.d(TAG, "Square:");
    }

    abstract float Acreage();

    abstract float Perimeter();
}
