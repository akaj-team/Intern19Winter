package asiantech.internship.summer.kotlincore.oop

class Circle(private val r: Float) : Shape() {
    private val PI = 3.14f

    override fun Acreage(): Float {
        return PI * r * r
    }

    override fun Perimeter(): Float {
        return r * 2f * PI
    }

    override fun printNameOfTheShape() {
        println("Circle:")
    }
}
