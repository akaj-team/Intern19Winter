package com.congtrinh

import java.util.*

/*
Bài 7. Dãy số Fibonacci được định nghĩa như sau:
 F0 =1, F1 = 1; Fn = Fn-1 + Fn-2 với n>=2. Hãy viết chương trình tìm số Fibonacci thứ n.
 */
object Bai7 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter n: ")
        val n = scanner.nextInt()
        print("F($n) = ${fibonacci(n)}")
    }

    private fun fibonacci(n: Int): Int {
        if (n == 0) return 1
        else if (n == 1) return 1
        else return fibonacci(n - 1) + fibonacci(n - 2)
    }
}