package asiantech.internship.summer.Kotlin
import java.util.Scanner
object Totalanalytical {
    fun process(): Int {
        val input = Scanner(System.`in`)
        var n = 0
                n = input.nextInt()
        return n
    }

    fun New(n: Int): Int {
        var n = n
        var T = 0
        while (n > 0) {
            T += n % 10
            n /= 10
        }
        return T
    }

    //Ham kiem tra so nguyen to
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

    fun result(n: Int) {
        var n = n
        var i = 2
        while (n > 1) {
            if (incheck(i)) {
                if (n % i == 0) {
                    print("$i.")
                    n /= i
                } else
                    i++
            } else
                i++
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        print("Nhap n")
        val n = process()
        print("n= ")
        result(n)
        println("Tong cac chu so cua $n la: " + New(n))
    }
}
