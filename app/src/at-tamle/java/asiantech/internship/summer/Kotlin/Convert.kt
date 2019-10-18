package asiantech.internship.summer.Kotlin
import java.util.Scanner
object Convert {

    fun radix(n: Int, k: Int) {
        if (n >= k) radix(n / k, k)
        if (n % k > 9)
            println("%c" + n % k + 55)
        else
            print(n % k)
    }

    fun process(): Int {
        val input = Scanner(System.`in`)
        var n = 0

                n = input.nextInt()

        return n
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap n")
        val n = process()
        println("Nhap vao co so can chuyen sang b")
        val b = process()
        println("So $n chuyen sang co so $b thanh: ")
        radix(n, b)
    }
}


