package com.congtrinh

import java.util.Scanner

/*
Bài 14. Nhập số liệu cho dãy số thực a0 , a1 ,..., an-1 .
Hãy liệt kê các phần tử xuất hiện trong dãy đúng một lần.
 */

object Bai16 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Nhap n: ")
        val n = scanner.nextInt()
        var i = 0
        val array = IntArray(n)

        while (i < n) {
            println("Nhap phan tu thu " + (i + 1) + " ")
            array[i] = scanner.nextInt()
            i++
        }

        for (j in 0 until n) {
            if (countElement(array, j, array[j]) == 0) {
                println("Phan tu ${array[j]} xuat hien ${countElement(array, n, array[j])} lan")
            }
        }
    }

    private fun countElement(a: IntArray, n: Int, i: Int): Int {
        var count = 0
        for (j in 0 until n) {
            if (a[j] == i)
                count++
        }
        return count
    }
}