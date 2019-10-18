package asiantech.internship.summer.Kotlin
import java.util.Scanner
object InList {
    fun process(): Int {
        val input = Scanner(System.`in`)
        var n = 0
                n = input.nextInt()
        return n
    }
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

    fun Element(n: Int) {
        var i = 1
        var count = 0
        println("Cac so nguyen to nho hon $n la: ")
        while (i < n) {
            if (incheck(i)) {
                print(" $i")
                count++

            }
            i++

        }
        println("\n Co $count so thoa man")

    }

    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap n")
        val n = process()
        Element(n)
        val f = IntArray(n)
        f[0] = 1
        f[1] = 1
        var i = 1
        print("Cac so Fibonanci nho hon $n la : \n 1")
        while (f[i] < n) {
            print(" " + f[i])
            i++
            f[i] = f[i - 1] + f[i - 2]

        }
        println("\n Co $i so thoa man")
    }
}
