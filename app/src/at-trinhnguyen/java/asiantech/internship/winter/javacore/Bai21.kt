package com.congtrinh

import java.util.*
import kotlin.math.sqrt

/*
Bài 21. Viết chương trình nhập một số nguyên dương n và thực hiện các chức năng sau:
Tính tổng các chữ số của n.
Phân tích n thành các thừa số nguyên tố.
 */
object Bai21 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter n: ")
        var n = scanner.nextInt()
        var i = 2
        println("Sum = ${sumElement(n)}")
        print("$n = ")
        while (n > 1) {
            if (isPrimeNumber(i)) {
                if (n % i == 0) {
                    if (n == i) print(i)
                    else print("$i x ")
                    n /= i
                } else i++
            } else i++
        }
    }

    private fun isPrimeNumber(n: Int): Boolean {
        if (n < 2) return false
        for (i in 2..sqrt(n.toDouble()).toInt()) {
            if (n % i == 0) return false
        }
        return true
    }
    private fun sumElement(n: Int): Int {
        var sum = 0
        var m = n
        while (m > 0) {
            sum += m % 10
            m /= 10
        }
        return sum
    }
}