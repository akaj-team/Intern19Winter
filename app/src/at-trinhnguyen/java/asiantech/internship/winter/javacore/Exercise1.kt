package asiantech.internship.winter.javacore

import java.util.*

/*
Bài 1. Viết chương trình tìm ước số chung lớn nhất,
 bội số chung nhỏ nhất của hai số tự nhiên a và b.
 */
object Exercise1 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter a: ")
        print("Enter b: ")
        val a = scanner.nextInt()
        val b = scanner.nextInt()

        println(greatestCommonDivisor(a, b))
        println(leastCommonMultiple(a, b))
    }

    private fun greatestCommonDivisor(a: Int, b: Int): Int {
        if (b == 0) return a
        else return greatestCommonDivisor(b, a % b)
    }

    private fun leastCommonMultiple(a: Int, b: Int): Int {
        return a * b / greatestCommonDivisor(a, b)
    }
}
