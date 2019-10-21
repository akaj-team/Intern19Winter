package asiantech.internship.summer.oop

import java.util.*

object JavaCore {

    @JvmStatic
    fun main(args: Array<String>) {
        val shapes = ArrayList<Shape>()
        shapes.add(Rectangle(3.5f, 2.5f))
        shapes.add(Rectangle(2.5f, 2.5f))
        shapes.add(Circle(3.67f))
        for (shape in shapes) {
            shape.printNameOfTheShape()
            println("Arceage: " + shape.Acreage())
            println("Perimeter: " + shape.Perimeter())
        }
    }
}
