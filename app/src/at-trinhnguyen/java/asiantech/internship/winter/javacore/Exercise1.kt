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

        println(findGreatestCommonDivisor(a, b))
        println(findLeastCommonMultiple(a, b))
    }

    private fun findGreatestCommonDivisor(a: Int, b: Int): Int {
        return if (b == 0) a
        else findGreatestCommonDivisor(b, a % b)
    }

    private fun findLeastCommonMultiple(a: Int, b: Int): Int {
        return a * b / findGreatestCommonDivisor(a, b)
    }
}
