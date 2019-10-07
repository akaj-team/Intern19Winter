package com.congtrinh

import java.util.*
import kotlin.math.sqrt

/*
Bài 4. Viết chương trình phân tích một số nguyên thành các thừa số nguyên tố
Ví dụ: Số 28 được phân tích thành 2 x 2 x 7
 */
object Bai4 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter n: ")
        var n = scanner.nextInt()
        var i = 2
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

    fun isPrimeNumber(n: Int): Boolean {
        if (n < 2) return false
        for (i in 2..sqrt(n.toDouble()).toInt()) {
            if (n % i == 0) return false
        }
        return true
    }
}