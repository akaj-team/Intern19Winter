package asiantech.internship.summer.kotlincore.exercise24

import asiantech.internship.summer.kotlincore.isPrime
import asiantech.internship.summer.kotlincore.isReversible

object Exercise24 {
    private fun isEachDigitIsAPrimeNumber(number: Int): Boolean {
        var n = number
        while (n != 0) {
            if (!(n % 10).isPrime()) {
                return false
            }
            n /= 10
        }
        return true
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var count = 0
        println("cac so tu 5-7 chu so thoa man dieu kien la: ")
        var i = 10001
        while (i < 10000000) {
            if (i.isPrime() && isEachDigitIsAPrimeNumber(
                            i) && i.isReversible()) {
                println(" $i")
                count++
            }
            i += 2
        }
        println("\n Co $count so thoa man")
    }
}
