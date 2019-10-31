package asiantech.internship.summer.kotlin

import java.util.Scanner

object Prime {
    private fun process(n: Int) {
        if (n > 2)
            print("2")
        var i = 3
        while (i < n) {
            if (element(i))
                print(" $i")
            i += 2
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

    private fun list(): Int {
        val input = Scanner(System.`in`)
        var check = false
        var n = 0
        while (!check) {
            try {
                n = input.nextInt()
                check = true
            } catch (e: Exception) {
                println("phai nhap so, hay nhap lai")
                input.nextLine()
            }

        }
        return n
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap n")
        val n = list()
        println("Cac so nguyen to nho hon $n ")
        process(n)
    }
}