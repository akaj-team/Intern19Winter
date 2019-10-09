package asiantech.internship.winter.javacore

import kotlin.math.sqrt

/*
Bài 25. Viết chương trình liệt kê các số nguyên có 7 chữ số thoả mãn:
Là số nguyên tố.
Là số thuận nghịch.
Tổng các chữ số của số đó là một số thuận nghịch
 */
object Exercise25 {
    @JvmStatic
    fun main(args: Array<String>) {
        for (i in 1000000..9999999) {
            if (isPrimeNumber(i)) {
                print("$i ")
            }
        }
        for (i in 1000000..9999999) {
            if (isReversible(i.toString())) {
                print("$i ")
            }
        }
        for (i in 1000000..9999999) {
            if (isReversible(sumElement(i).toString())) {
                print("$i ")
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

    private fun isReversible(n: String): Boolean {
        return n.equals(StringBuffer(n).reverse().toString())
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
