package asiantech.internship.summer.kotlin

import java.util.Arrays
import java.util.Scanner

//bai 26
object Sort {
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

    private fun locationMax(a: IntArray, n: Int): Int {
        var max = a[0]
        var key = 0
        for (j in 0 until n) {
            if (max < a[j]) {
                max = a[j]
                key = j
            }
        }
        return key
    }

    private fun list(a: IntArray, begin: Int, end: Int) {
        println()
        var i: Int
        i = begin
        while (i < end) {
            print(" " + a[i])
            i++
        }
        println()
    }

    private fun locationMax2(a: IntArray, n: Int): Int {
        var i: Int
        var key = 0
        var max2 = 0
        i = 0
        while (i < n) {
            if (a[i] > max2 && a[i] != a[locationMax(a, n)]) {
                max2 = a[i]
                key = i
            }
            i++
        }
        return key
    }

    private fun add(a: IntArray, n: Int, pt: Int) {
        a[0] = pt
        Arrays.sort(a)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        print("Nhap n= ")
        val n = process()
        val a = IntArray(n + 1)
        var i: Int
        i = 0
        while (i < n) {
            print("\n Nhap phan tu thu $i = ")
            a[i] = process()
            i++
        }
        i = 0
        while (i < n) {
            if (a[i] == a[locationMax2(a, n)]) println(" Phan tu thu " + i + " lon thu 2 trong mang a[" + i + "]= " + a[i])
            i++
        }
        Arrays.sort(a)
        list(a, 1, n + 1)
        print("Nhap phan tu muon them pt= ")
        val pt = process()
        add(a, n + 1, pt)
        list(a, 0, n)
    }
}