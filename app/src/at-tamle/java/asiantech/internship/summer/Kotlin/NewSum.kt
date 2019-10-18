package asiantech.internship.summer.Kotlin
import java.util.Scanner
object NewSum {
    fun process(): Int {
        val input = Scanner(System.`in`)
        var n = 0
        n = input.nextInt()
        return n
    }
    fun total(i: Long): Int {
        var i = i
        var sum = 0
        var n: Long
        while (i != 0L) {
            n = i % 10
            sum += n.toInt()
            i /= 10
        }
        return sum
    }
    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap n")
        val n = process()
        println("Tong cua so $n = " + total(n.toLong()))
    }
}
