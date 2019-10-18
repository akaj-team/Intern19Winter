package asiantech.internship.summer.Kotlin
import java.util.Scanner
object Prime {
    fun process(n: Int) {
        if (n > 2)
            print("2")
        var i = 3
        while (i < n) {
            if (element(i))
                print(" $i")
            i += 2
        }
    }

    fun element(n: Int): Boolean {
        if (n == 2 || n == 3)
            return true
        if (n == 1 || n % 2 == 0 || n % 3 == 0)
            return false
        var k = -1
        do {
            k += 6
            if (n % k == 0 || n % (k + 2) == 0)
                break
        } while (k * k < n)// k < sqrt(n);
        return k * k > n// return k > sqrt(n).
    }

    fun list(): Int {
        val input = Scanner(System.`in`)

        var n = 0
                n = input.nextInt()

        return n
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap n")
        val n = list()
        println("Cac so nguyen to nho hon $n ")
        process(n)
    }
}
