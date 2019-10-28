package asiantech.internship.summer.kotlin

import java.util.Scanner
import kotlin.math.sqrt

object InList {
    private fun process(): Int {
        val input = Scanner(System.`in`)
        var check = false
        var n = 0
        while (!check) {
            print(" ")
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

    private fun element(n: Int) {
        var i = 1
        var count = 0
        println("Cac so nguyen to nho hon $n la: ")
        while (i < n) {
            if (inCheck(i)) {
                print(" $i")
                count++

            }
            i++

        }
        println("\n Co $count so thoa man")

    }

    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap n")
        val n = process()
        element(n)
        val f = IntArray(n)
        f[0] = 1
        f[1] = 1
        var i = 1
        print("Cac so Fibonanci nho hon $n la : \n 1")
        while (f[i] < n) {
            print(" " + f[i])
            i++
            f[i] = f[i - 1] + f[i - 2]

        }
        println("\n Co $i so thoa man")
    }
}
