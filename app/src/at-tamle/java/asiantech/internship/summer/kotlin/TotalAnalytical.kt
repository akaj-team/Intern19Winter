package asiantech.internship.summer.kotlin

import java.util.Scanner
import kotlin.math.sqrt

object TotalAnalytical {
    private fun process(): Int {
        val input = Scanner(System.`in`)
        var check = false
        var n = 0
        while (!check) {
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

    private fun input(n: Int): Int {
        var n = n
        var k = 0
        while (n > 0) {
            k += n % 10
            n /= 10
        }
        return k
    }

    private fun inCheck(n: Int): Boolean {
        if (n > 1) {
            var i = 2
            while (i <= sqrt(n.toDouble())) {
                if (n % i == 0) return false
                i++
            }
            return true
        } else
            return false
    }

    private fun result(n: Int) {
        var n = n
        var i = 2
        while (n > 1) {
            if (inCheck(i)) {
                if (n % i == 0) {
                    print("$i.")
                    n /= i
                } else
                    i++
            } else
                i++
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        print("Nhap n")
        val n = process()
        print("n= ")
        result(n)
        println("Tong cac chu so cua $n la: " + input(n))
    }
}
