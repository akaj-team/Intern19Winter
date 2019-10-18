package asiantech.internship.summer.Kotlin
import java.util.Scanner
object ListPrime {
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
    fun New(n: Int) {
        var count = 0
        print("\nCac uoc cua $n la:")
        for (i in 1..n) {
            if (n % i == 0) {
                print(" $i")
                count++

            }

        }
        println("\nCo $count uoc")
    }
    fun inNew(n: Int) {
        var count = 0
        print("\nCac uoc cua $n la:")
        for (i in 1..n) {
            if (n % i == 0 && incheck(i)) {
                print(" $i")
                count++
            }
        }
        println("\nCo $count uoc la so nguyen to")
    }
    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap n")
        val n = process()
        New(n)
        inNew(n)
    }
}
