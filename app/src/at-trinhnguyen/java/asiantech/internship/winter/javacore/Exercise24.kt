package asiantech.internship.winter.javacore

import kotlin.math.sqrt

/*
Bài 24. Viết chương trình liệt kê các số nguyên có từ 5 đến 7 chữ số thoả mãn:
Là số nguyên tố.
Là số thuận nghịch.
Mỗi chữ số đều là số nguyên tố
 */
object Exercise24 {
    @JvmStatic
    fun main(args: Array<String>) {
        for (i in 10000..9999999) {
            if (isPrimeNumber(i)) {
                print("$i ")
            }
        }
        println()
        for (i in 10000..9999999) {
            if (isReversible(i.toString())) {
                print("$i ")
            }
        }
        for (i in 12345..9999999) {
            var n = i
            var charIsPrime = true
            while (n > 0) {
                if ((n % 10) == 0 || (n % 10) == 1 || (n % 10) == 4 || (n % 10) == 6 || (n % 10) == 8 || (n % 10) == 9) {
                    charIsPrime = false
                    break
                }
                n /= 10
            }
            if (charIsPrime) println(i)
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
}
