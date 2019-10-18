package asiantech.internship.summer.Kotlin

import java.util.Scanner

object Ascending {
    fun process(): Int {
        val input = Scanner(System.`in`)
        var n = 0
        n = input.nextInt()
        return n
    }

    private fun number(): Float {
        val input = Scanner(System.`in`)
        var check = false
        var n = 0f
        while (!check) {
            print(" ")
            try {
                n = input.nextInt().toFloat()
                check = true
            } catch (e: Exception) {
                println(" Ban phai nhap so! hay nhap lai")
                input.nextLine()
            }

        }
        return n
    }

    private fun minNumber(a: FloatArray, n: Int): Int {
        var min = a[0]
        var key = 0
        for (j in 0 until n) {
            if (min > a[j]) {
                min = a[j]
                key = j
            }
        }
        return key
    }

    private fun maxNumber(a: FloatArray, n: Int): Float {
        var max = a[0]
        for (j in 0 until n) {
            if (max < a[j]) max = a[j]
        }
        return max
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val n: Int
        var i: Int
        println("Nhap n= ")
        n = process()
        val array = FloatArray(n)
        i = 0
        while (i < n) {
            println("Nhap phan tu thu " + (i + 1) + " ")
            array[i] = number()
            i++
        }
        i = 0
        println("Sap xep theo thu tu tang dan")
        while (i < n) {
            println(" " + array[minNumber(array, n)])
            array[minNumber(array, n)] = maxNumber(array, n)
            i++
        }
    }
}
