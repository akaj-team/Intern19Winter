package asiantech.internship.winter.javacore

import java.util.*
import kotlin.math.sqrt

/*
Bài 6. Viết chương trình liệt kê n số nguyên tố đầu tiên.
 */object Bai6 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter n: ")
        val n = scanner.nextInt()
        var i = 2
        var count = 0
        while (count < n) {
            if (isPrimeNumber(i)) {
                print("$i ")
                count++
            }
            i++
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