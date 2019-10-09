package asiantech.internship.winter.javacore

import java.util.Scanner

/*
Bài 14. Nhập số liệu cho dãy số thực a0 , a1 ,..., an-1 .
Hãy liệt kê các phần tử xuất hiện trong dãy đúng một lần.
 */

object Bai14 {

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

        print("Cac phan tu trong day xuat hien 1 lan: ")
        i = 0
        while (i < n) {
            if (countElement(array, n, array[i]) == 2)
                print(" " + array[i])
            i++
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