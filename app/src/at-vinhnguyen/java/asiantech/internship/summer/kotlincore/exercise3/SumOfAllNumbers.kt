package asiantech.internship.summer.kotlincore.exercise3

import asiantech.internship.summer.kotlincore.inputIntNumber
import asiantech.internship.summer.kotlincore.sumOfNumber

object SumOfAllNumbers {

    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap so: ")
        val a = inputIntNumber()
        println("Tong cac chu so: " + a.sumOfNumber())
    }
}
