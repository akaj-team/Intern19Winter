package asiantech.internship.winter.javacore

import java.util.*

/*
Bài 23. Viết chương trình nhập một số nguyên dương n và thực hiện các chức năng sau:
Liệt kê n số nguyên tố đầu tiên.
Liệt kê n số Fibonaci đầu tiên.
 */
object Exercise23 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter n: ")
        val n = scanner.nextInt()
        var i = 0
        var j = 2
        while (i < n) {
            if (i.isPrimeNumber()) {
                print("$j ")
                i++
            }
            j++
        }
        i = 0
        println()
        while (i < n) {
            print("${i.fibonacci()} ")
            i++
        }
    }
}
