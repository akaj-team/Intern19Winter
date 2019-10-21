package asiantech.internship.summer.kotlincore.oop

abstract class Shape {

  internal open fun printNameOfTheShape() {
    println("Square:")
  }

  internal abstract fun Acreage(): Float

  internal abstract fun Perimeter(): Float
}
