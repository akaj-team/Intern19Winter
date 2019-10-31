package asiantech.internship.summer.kotlin

import java.util.Scanner
import kotlin.math.sqrt

object List {
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

    private fun incheck(n: Int): Boolean {
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

    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap n= ")
        val n = process()
        val f = IntArray(n)
        f[0] = 1
        f[1] = 1
        var i = 1
        var count = 1
        print("Cac so Fibonanci nho hon $n la so nguyen to: \n 1")
        while (f[i] < n) {
            if (incheck(f[i])) {
                print(" " + f[i])
                count++
            }
            i++
            f[i] = f[i - 1] + f[i - 2]
        }
        println("\n Co $count so thoa man")
    }
}

