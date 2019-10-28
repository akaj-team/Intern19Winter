package asiantech.internship.summer.kotlin.exoop

abstract class Shape {
    abstract fun perimeter(): Double

    abstract fun area(): Double

    open fun print(): String {
        return "Default"
    }
}
