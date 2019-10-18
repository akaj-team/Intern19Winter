package asiantech.internship.summer.Kotlin
import java.util.Scanner
object Factor {
    fun process(n: Int) {
        var n = n
        var i = 2
        while (n > 1) {
            if (n % i == 0) {
                print(i)
                n /= i
                if (n > 1) print(".")
            } else
                i++
        }
    }
    fun Input(): Int {
        val input = Scanner(System.`in`)
        var n = 0
        n = input.nextInt()
        return n
    }
    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap n")
        val n = Input()
        print("$n = ")
        process(n)
    }
}
