package asiantech.internship.winter.javacore

import java.util.*

/*
Bài 6. Viết chương trình liệt kê n số nguyên tố đầu tiên.
 */
object Exercise6 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter n: ")
        val n = scanner.nextInt()
        var i = 2
        var count = 0
        while (count < n) {
            if (i.isPrimeNumber()) {
                print("$i ")
                count++
            }
            i++
        }
    }
}
