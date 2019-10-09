package asiantech.internship.winter.javacore

import java.util.*
import kotlin.math.sqrt

/*
Bài 5. Viết chương trình liệt kê tất cả các số nguyên tố nhỏ hơn n cho trước.
 */
object Bai5 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter n: ")
        val n = scanner.nextInt()
        for (i in 2..n) {
            if (isPrimeNumber(i))
                print("$i ")
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

