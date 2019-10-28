package asiantech.internship.winter.kotlin

import java.util.Scanner

object Common {
    fun process(): Int {
        val input = Scanner(System.`in`)
        var check = false
        var n = 0
        while (!check) {
            println(" ")
            try {
                n = input.nextInt()
                check = true
            } catch (e: Exception) {
                println("Ban phai nhap so! hay nhap lai...")
                input.nextLine()
            }

        }
        return n
    }

    private fun comConvention(a: Int, b: Int): Int {
        var a=a
        var b=b
        while (a != b) {
            if (a > b)
                a = a - b
            else
                b = b - a
        }
        return a
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap a")
        val a = process()
        println("Nhap b")
        val b = process()
        println("Uoc chung lon nhat cua $a va $b la: " + comConvention(a, b))
        println("Boi chung nho nhat cua $a va $b la: " + a * b / comConvention(a, b))
    }
}