package asiantech.internship.winter.javacore

import java.util.*

/*
Bài 21. Viết chương trình nhập một số nguyên dương n và thực hiện các chức năng sau:
Tính tổng các chữ số của n.
Phân tích n thành các thừa số nguyên tố.
 */
object Exercise21 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter n: ")
        var n = scanner.nextInt()
        var i = 2
        println("Sum = ${n.sum()}")
        print("$n = ")
        while (n > 1) {
            if (i.isPrimeNumber()) {
                if (n % i == 0) {
                    if (n == i) print(i)
                    else print("$i x ")
                    n /= i
                } else {
                    i++
                }
            } else {
                i++
            }
        }
    }
}
