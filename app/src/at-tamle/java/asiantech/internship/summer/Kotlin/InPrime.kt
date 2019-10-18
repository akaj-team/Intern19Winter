package asiantech.internship.summer.Kotlin
object InPrime {
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
        val New = StringBuilder()
        val str = "" + n
        New.append(str)
        val check = "" + New.reverse()
        return if (str == check)
            true
        else
            false
    }

    fun element(n: Int): Boolean {
        var n = n
        while (n != 0) {
            if (!incheck(n % 10)) return false
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
            if (incheck(i) && element(i) && testTotal(i)) {
                println(" $i")
                count++
            }
            i += 2
        }
        println("\n Co $count so thoa man")
    }
}
