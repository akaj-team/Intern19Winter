package asiantech.internship.summer.kotlin

import java.util.Scanner
import kotlin.math.sqrt

object ListPrime {
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

    private fun condition(n: Int) {
        var count = 0
        print("\nCac uoc cua $n la:")
        for (i in 1..n) {
            if (n % i == 0) {
                print(" $i")
                count++

            }

        }
        println("\nCo $count uoc")
    }

    private fun inNew(n: Int) {
        var count = 0
        print("\nCac uoc cua $n la:")
        for (i in 1..n) {
            if (n % i == 0 && inCheck(i)) {
                print(" $i")
                count++
            }
        }
        println("\nCo $count uoc la so nguyen to")
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap n")
        val n = process()
        condition(n)
        inNew(n)
    }
}
