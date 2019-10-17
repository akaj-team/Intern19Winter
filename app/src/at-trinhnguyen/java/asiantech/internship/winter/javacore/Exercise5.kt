package asiantech.internship.winter.javacore

import java.util.*

/*
Bài 5. Viết chương trình liệt kê tất cả các số nguyên tố nhỏ hơn n cho trước.
 */
object Exercise5 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter n: ")
        val n = scanner.nextInt()
        for (i in 2..n) {
            if (i.isPrimeNumber()) {
                print("$i ")
            }
        }
    }
}
