package asiantech.internship.summer.Kotlin
import java.util.Scanner
object fibonacci {
    private fun process(): Int {
        val input = Scanner(System.`in`)
        var n = 0
        n = input.nextInt()
        return n
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap n")
        val n = process()
        val f = IntArray(n + 1)
        f[0] = 1
        f[1] = 1
        for (i in 2..n) {
            f[i] = f[i - 1] + f[i - 2]
        }
        println("So Fibonanci thu $n la: f[" + n + "]= " + f[n])
    }
}
