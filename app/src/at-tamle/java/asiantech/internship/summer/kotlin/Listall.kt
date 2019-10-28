package asiantech.internship.summer.kotlin

import java.util.Scanner
import kotlin.math.sqrt

object Listall {
    private fun process(): Int {
        val input = Scanner(System.`in`)
        var check = false
        var n = 0
        while (!check) {
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

    private fun total(n: Int): Int {
        var n = n
        var T = 0
        while (n > 0) {
            T += n % 10
            n /= 10
        }
        return T
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap S= ")
        val s = process()
        var i = 1000
        var count = 0
        println("Cac so nguyen to co tong cac chu so co tong bang $s la: ")

        while (i <= 99999) {
            if (inCheck(i)) {
                if (total(i) == s) {
                    println(" $i")
                    count++
                } else {
                    i++
                    continue
                }
            }
            i++
        }
        println("Co $count so thoa man")
    }
}
