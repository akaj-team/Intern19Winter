package asiantech.internship.winter.javacore

import java.util.*
import kotlin.math.sqrt

/*
Bài 19. Viết chương trình liệt kê tất cả các số nguyên tố có 5 chữ số
sao cho tổng của các chữ số trong mỗi số nguyên tố đều bằng S cho trước.
 */
object Exercise19 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter sum : ")
        val sum = scanner.nextInt()
        for (i in 10000..99999) {
            if (isPrimeNumber(i)) {
                if (sumElement(i) == sum)
                    println(i)
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
