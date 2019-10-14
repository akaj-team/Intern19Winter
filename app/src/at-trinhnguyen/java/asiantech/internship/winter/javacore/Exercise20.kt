package asiantech.internship.winter.javacore

import java.util.*

/*
Bài 20. Nhập một số tự nhiên n.
Hãy liệt kê các số Fibonaci nhỏ hơn n là số nguyên tố.
 */
object Exercise20 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter n: ")
        val n = scanner.nextInt()
        var i = 0
        while (i.fibonacci() < n) {
            if (i.fibonacci().isPrimeNumber()) {
                println(i.fibonacci())
            }
            i++
        }
    }
}
