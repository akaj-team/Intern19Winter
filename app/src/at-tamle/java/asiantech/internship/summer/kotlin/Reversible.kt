package asiantech.internship.summer.kotlin

object Reversible {
    fun process(n: Int): Boolean {
        val k = StringBuilder()
        val str = "" + n
        k.append(str)
        val check = "" + k.reverse()
        if (str == check)
            return true
        else
            return false
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var n: Int
        var count = 0
        n = 100000
        while (n <= 999999) {
            if (process(n)) {
                println(n)
                count++
            }
            n++
        }
        println("Co $count so thuan nghich co 6 chu so")
    }

}
