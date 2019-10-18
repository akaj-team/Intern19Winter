package asiantech.internship.summer.Kotlin

import java.util.Arrays
import java.util.Scanner
object InBigElement {
    fun process(): Int {
        val input = Scanner(System.`in`)
        var n = 0
                n = input.nextInt()
        return n
    }

    fun MaxInt(a: IntArray, n: Int): Int {
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

    fun inArray(a: IntArray, begin: Int, end: Int) {
        println()
        var i: Int
        i = begin
        while (i < end) {
            print(" " + a[i])
            i++
        }
        println()
    }
    fun Max2(a: IntArray, n: Int): Int {
        var i: Int
        var key = 0
        var Max2 = 0
        i = 0
        while (i < n) {
            if (a[i] > Max2 && a[i] != a[MaxInt(a, n)]) {
                Max2 = a[i]
                key = i
            }
            i++
        }
        return key
    }

    fun New(a: IntArray, n: Int, pt: Int) {
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
            if (a[i] == a[Max2(
                    a,
                    n
                )]
            ) println(" Phan tu thu " + i + " lon thu 2 trong mang a[" + i + "]= " + a[i])
            i++
        }
        Arrays.sort(a)
        inArray(a, 1, n + 1)
        print("Nhap phan tu muon them pt= ")
        val pt = process()
        New(a, n + 1, pt)
        inArray(a, 0, n)
    }
}
