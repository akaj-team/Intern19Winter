package asiantech.internship.summer.kotlin

import java.util.Scanner

object Subset {
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

    private fun result(a: IntArray, k: Int) {
        var i: Int
        println()
        i = 1
        while (i <= k) {
            print(" " + a[i])
            i++
        }
    }

    private fun tryBack(a: IntArray, n: Int, k: Int, i: Int) {
        var j: Int
        j = a[i - 1] + 1
        while (j <= n - k + i) {
            a[i] = j
            if (i == k)
            {result(a, k)}
            else
            {tryBack(a, n, k, i + 1)
            j++}
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap n")
        val n = process()
        val array = IntArray(n + 1)
        var k: Int
        println("Liet ke tat ca cac tap con k phan tu cua 1,2,..,$n : ")
        k = 1
        while (k <= n) {
            println("\n Tap con $k phan tu: ")
            tryBack(array, n, k, 1)
            k++
        }
    }
}

