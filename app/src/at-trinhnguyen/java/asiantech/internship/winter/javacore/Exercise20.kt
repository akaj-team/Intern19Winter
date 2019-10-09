package asiantech.internship.winter.javacore

import java.util.*
import kotlin.math.sqrt

/*
Bài 20. Nhập một số tự nhiên n.
Hãy liệt kê các số Fibonaci nhỏ hơn n là số nguyên tố.
 */
object Exercise20 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter n: ")
        val n = scanner.nextInt()
        var i = 0
        while (fibonacci(i) < n) {
            if (isPrimeNumber(fibonacci(i))) {
                println(fibonacci(i))
            }
            i++
        }
    }

    private fun isPrimeNumber(n: Int): Boolean {
        if (n < 2) return false
        for (i in 2..sqrt(n.toDouble()).toInt()) {
            if (n % i == 0) return false
        }
        return true
    }

    private fun fibonacci(n: Int): Int {
        if (n == 0) return 1
        else if (n == 1) return 1
        else return fibonacci(n - 1) + fibonacci(n - 2)
    }
}
