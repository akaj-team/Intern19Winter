package asiantech.internship.summer.kotlin.ex01

import asiantech.internship.summer.kotlin.common.Common

object CommonDivisor {

    @JvmStatic
    fun main(args: Array<String>) {
        val a = Common.input("Nhập số a: ")
        val b = Common.input("Nhập số b: ")
        val divisor = greatestCommonDivisor(a, b)
        println("Divisor: $divisor")
        println("Least Common Multiple: " + leastCommonMultiple(a, b, divisor))
    }

    private fun greatestCommonDivisor(a: Int, b: Int): Int {
        if (b == 0) {
            return a
        }
        return greatestCommonDivisor(b, a % b)
    }

    private fun leastCommonMultiple(a: Int, b: Int, c: Int): Int {
        return a * b / c
    }
}
