package asiantech.internship.summer.kotlin.exoop

import java.util.ArrayList

object ShapeMain {
    @JvmStatic
    fun main(args: Array<String>) {
        val rectangle = Rectangle(3, 4)
        val square = Square(5)
        val circle = Circle(3)

        val shapes = ArrayList<Shape>()
        shapes.add(circle)
        shapes.add(square)
        shapes.add(rectangle)

        for (shape in shapes) {
            println(shape.print() + ": " + shape.perimeter() + " - " + shape.area())
        }
    }
}
