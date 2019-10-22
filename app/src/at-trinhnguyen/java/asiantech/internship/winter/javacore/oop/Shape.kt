package asiantech.internship.winter.javacore.oop

abstract class Shape {
    internal abstract fun area(): Double

    internal abstract fun circumference(): Double

    internal open fun print() {
        print("Default")
    }
}
