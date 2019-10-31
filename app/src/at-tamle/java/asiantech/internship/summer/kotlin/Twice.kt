package asiantech.internship.summer.kotlin

import java.util.Scanner

object Twice {
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

    private fun countElement(a: IntArray, n: Int, i: Int): Int {
        var count = 0
        for (j in 0 until n) {
            if (a[j] == i)
                count++
        }
        return count
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val n: Int
        var i: Int
        println("Nhap n= ")
        n = process()
        val array = IntArray(n)
        i = 0
        while (i < n) {
            print("Nhap phan tu thu " + (i + 1) + " ")
            array[i] = process()
            i++
        }
        println("Cac phan tu trong day xuat hien 2 lan: ")
        i = 0
        while (i < n) {
            if (countElement(array, n, array[i]) == 2 && countElement(array, i, array[i]) == 0) {
                print(" " + array[i])
            }
            i++
        }

    }
}
