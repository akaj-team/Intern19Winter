package asiantech.internship.summer.kotlin

import java.util.Scanner

object InElement {
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
            println("Nhap phan tu thu " + (i + 1) + " ")
            array[i] = process()
            i++
        }
        i = 0
        while (i < n) {
            if (countElement(array, i, array[i]) == 0) {
                println(
                        "Phan tu " + array[i] + " xuat hien " + countElement(
                                array,
                                n,
                                array[i]
                        ) + " lan"
                )
            }
            i++
        }
    }

}
