package asiantech.internship.summer.kotlincore.exercise8

import java.util.*

object Reversible {

  @JvmStatic
  fun main(args: Array<String>) {
    println("Nhap chuoi: ")
    val str = Scanner(System.`in`).nextLine()
    val strBuilder = StringBuilder(str)
    if (strBuilder.reverse().toString().equals(str)) {
      println("Chuoi thuan nghich")
    } else {
      println("Khong phai chuoi thuan nghich")
    }
  }
}
