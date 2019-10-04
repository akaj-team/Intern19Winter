package com.congtrinh

import java.util.*
import kotlin.math.sqrt

/*
Bài 22. Viết chương trình nhập một số nguyên dương n và thực hiện các chức năng sau:
Liệt kê các ước số của n. Có bao nhiêu ước số.
Liệt kê các ước số là nguyên tố của n.
 */
object Bai22 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter n: ")
        val n = scanner.nextInt()
        var count = 0
        for (i in 1..n) {
            if (n % i == 0) {
                print("$i ")
                count++
            }
        }
        println("\n$count")

        for (i in 1..n) {
            if (n % i == 0) {
                if (isPrimeNumber(i)) {
                    print("$i ")
                }
            }
        }
    }

    private fun isPrimeNumber(n: Int): Boolean {
        if (n < 2) return false
        for (i in 2..sqrt(n.toDouble()).toInt()) {
            if (n % i == 0) return false
        }
        return true
    }
}