package asiantech.internship.summer.kotlin

import kotlin.math.sqrt

object InPrime {
    private fun inCheck(n: Int): Boolean {
        if (n > 1) {
            var i = 2
            while (i <= sqrt(n.toDouble())) {
                if (n % i == 0) return false
                i++
            }
            return true
        } else
            return false
    }

    private fun testTotal(n: Int): Boolean {
        val list = StringBuilder()
        val str = "" + n
        list.append(str)
        val check = "" + list.reverse()
         if (str == check)
           return true
        else
            return false
    }

    private fun element(n: Int): Boolean {
        var n = n
        while (n != 0) {
            if (!inCheck(n % 10)) return false
            n /= 10
        }
        return true
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var i: Int
        var count = 0
        println("cac so tu 5-7 chu so thoa man dieu kien la: ")
        i = 22223
        while (i < 7777777) {
            if (inCheck(i) && element(i) && testTotal(i)) {
                println(" $i")
                count++
            }
            i += 2
        }
        println("\n Co $count so thoa man")
    }
}
