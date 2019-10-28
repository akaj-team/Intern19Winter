package asiantech.internship.summer.kotlincore.oop

class Rectangle(private val a: Float, private val b: Float) : Shape() {

  override fun Acreage(): Float {
    return a * b
  }

  override fun Perimeter(): Float {
    return (a + b) * 2
  }

  override fun printNameOfTheShape() {
    if (a == b) {
      super.printNameOfTheShape()
      return
    }
    println("Rectangle:")
  }
}
