package com.congtrinh

import java.util.*

/*
Bài 1. Viết chương trình tìm ước số chung lớn nhất,
 bội số chung nhỏ nhất của hai số tự nhiên a và b.
 */
object Bai1 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        val a = scanner.nextInt()
        val b = scanner.nextInt()

        println(UCLN(a, b))
        println(BCNN(a, b))
    }

    private fun UCLN(a: Int, b: Int): Int {
        if (b == 0) return a
        else return UCLN(b, a % b)
    }

    private fun BCNN(a: Int, b: Int): Int {
        return a * b / UCLN(a, b)
    }
}