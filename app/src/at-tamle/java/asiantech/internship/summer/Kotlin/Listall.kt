package asiantech.internship.summer.Kotlin
import java.util.Scanner
object Listall {
    fun process(): Int {
        val input = Scanner(System.`in`)
        var n = 0
                n = input.nextInt()
        return n
    }
    fun Check(n: Int): Boolean {
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

    fun total(n: Int): Int {
        var n = n
        var T = 0
        while (n > 0) {
            T += n % 10
            n /= 10
        }
        return T
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap S= ")
        val s = process()
        var i: Int
        var count = 0
        println("Cac so nguyen to co tong cac chu so co tong bang $s la: ")
        i = 10000
        while (i <= 99999) {
            if (Check(i)) {
                if (total(i) == s) {
                    println(" $i")
                    count++
                } else {
                    i++
                    continue
                }
            }
            i++
        }
        println("Co $count so thoa man")
    }
}
