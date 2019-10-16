package asiantech.internship.summer.exercise3

import inputIntNumber
import sumOfNumber

object SumOfAllNumbers {

    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap so: ")
        val a = inputIntNumber()
        println("Tong cac chu so: " + a.sumOfNumber())
    }
}
