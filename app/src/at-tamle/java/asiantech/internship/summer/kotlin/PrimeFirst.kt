package asiantech.internship.summer.kotlin

import java.util.Scanner

object PrimeFirst {
    private fun process(n: Int) {
        var i = 2
        var count = 0
        while (count < n) {
            if (element(i)) {
                print(" $i")
                count++
            }
            i++
        }
    }

    private fun element(n: Int): Boolean {
        if (n == 2 || n == 3)
            return true
        if (n == 1 || n % 2 == 0 || n % 3 == 0)
            return false
        var k = -1
        do {
            k += 6
            if (n % k == 0 || n % (k + 2) == 0)
                break
        } while (k * k < n)
        return k * k > n
    }

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

    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap n")
        val n = process()
        println("$n so nguyen to dau tien la: ")
        process(n)
    }
}
