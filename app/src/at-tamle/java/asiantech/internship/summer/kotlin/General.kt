package asiantech.internship.summer.kotlin

import kotlin.math.sqrt

object General {
    private fun inCheck(n: Int): Boolean {
        if (n > 1) {
            var i = 2
            while (i <= sqrt(n.toDouble())) {
                if (n % i == 0)
                    return false
                i++
            }
            return true
        } else
            return false
    }

    private fun testTotal(n: Int): Boolean {
        val test = StringBuilder()
        val str = "" + n
        test.append(str)
        val check = "" + test.reverse()
        return if (str == check)
            true
        else
            false
    }

    private fun equal(n: Int): Boolean {
        var n = n
        var k = 0
        while (n != 0) {
            k += n % 10
            if (!inCheck(k)) return false
            n /= 10
        }
        return true
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var i: Int
        var count = 0
        println("cac so tu 7 chu so thoa man dieu kien la: ")
        i = 22223
        while (i < 7777777) {
            if (inCheck(i) && equal(i) && testTotal(i)) {
                println(" $i")
                count++
            }
            i += 2
        }
        println("\n Co $count so thoa man")
    }
}
