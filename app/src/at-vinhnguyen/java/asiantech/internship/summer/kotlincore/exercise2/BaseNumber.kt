package asiantech.internship.summer.kotlincore.exercise2

import inputIntNumber

object BaseNumber {

  @JvmStatic
  fun main(args: Array<String>) {
    println("Nhap he co so ban can chuyen: ")
    val coSo = inputIntNumber()
    println("Nhap so o he thap phan ban can chuyen: ")
    val so = inputIntNumber()
    println("So sau khi chuyen: " + convertBaseNumber(
        coSo, so))
  }

  private fun convertBaseNumber(baseNumber: Int, number: Int): Int {
    var number = number
    var i = 1
    var result = 0
    while (number > 1) {
      result += number % baseNumber * i
      number /= baseNumber
      i *= 10
    }
    return result
  }
}
