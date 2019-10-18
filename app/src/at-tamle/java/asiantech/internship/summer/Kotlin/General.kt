package asiantech.internship.summer.Kotlin

import android.util.Log

object General {

    fun incheck(n: Int): Boolean {
        if (n > 1) {
            var i = 2
            while (i <= Math.sqrt(n.toDouble())) {
                if (n % i == 0) return false
                i++
            }
            return true
        } else
            return false
    }

    fun testTotal(n: Int): Boolean {
        val test = StringBuilder()
        val str = "" + n
        test.append(str)
        val check = "" + test.reverse()
        return if (str == check)
            true
        else
            false
    }

    fun equal(n: Int): Boolean {
        var n = n
        var T = 0
        while (n != 0) {
            T += n % 10
            if (!incheck(T)) return false
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
            if (incheck(i) && equal(i) && testTotal(i)) {
                println(" $i")
                count++
            }
            i += 2
        }
        println("\n Co $count so thoa man")
    }
}
