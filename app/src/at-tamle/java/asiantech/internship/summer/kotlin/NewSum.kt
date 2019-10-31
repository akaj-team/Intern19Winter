package asiantech.internship.summer.kotlin

import java.util.Scanner

object NewSum {
    private fun process(): Int {
        val input = Scanner(System.`in`)
        var check = false
        var n = 0
        while (!check) {
            println(" ")
            try {
                n = input.nextInt()
                check = true
            } catch (e: Exception) {
                println("hay nhap lai")
                input.nextLine()
            }

        }
        return n
    }

    private fun total(i: Long): Int {
        var i = i
        var sum = 0
        var n: Long
        while (i != 0L) {
            n = i % 10
            sum += n.toInt()
            i /= 10
        }
        return sum
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap n")
        val n = process()
        println("Tong cua so $n = " + total(n.toLong()))
    }
}
