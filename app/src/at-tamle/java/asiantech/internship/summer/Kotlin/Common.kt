package asiantech.internship.winter.Kotlin
import java.util.Scanner
object Common {
    fun process(): Int {
        val input = Scanner(System.`in`)
        var n = 0
                n = input.nextInt()
        return n
    }

    fun comConvention(a: Int, b: Int): Int {
        var a = a
        var b = b
        while (a != b) {
            if (a > b)
                a = a - b
            else
                b = b - a
        }
        return a
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap a")
        val a = process()
        println("Nhap b")
        val b = process()
        println("Uoc chung lon nhat cua $a va $b  la: " + comConvention(a, b))
        println("Boi chung nho nhat cua $a va $b la: " + a * b / comConvention(a, b))
    }
}
