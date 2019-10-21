package asiantech.internship.summer.oop

abstract class Shape {

    internal open fun printNameOfTheShape() {
        println("Square:")
    }

    internal abstract fun Acreage(): Float

    internal abstract fun Perimeter(): Float
}
