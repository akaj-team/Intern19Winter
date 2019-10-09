package asiantech.internship.winter.javacore

import java.util.*
import kotlin.math.sqrt

/*
Bài 23. Viết chương trình nhập một số nguyên dương n và thực hiện các chức năng sau:
Liệt kê n số nguyên tố đầu tiên.
Liệt kê n số Fibonaci đầu tiên.
 */
object Exercise23 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter n: ")
        val n = scanner.nextInt()
        var i = 0
        var j = 2
        while (i < n) {
            if (isPrimeNumber(j)) {
                print("$j ")
                i++
            }
            j++
        }
        i = 0
        println()
        while (i < n) {
            print("${fibonacci(i)} ")
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
