package asiantech.internship.winter.javacore

import java.util.*

/*
Bài 4. Viết chương trình phân tích một số nguyên thành các thừa số nguyên tố
Ví dụ: Số 28 được phân tích thành 2 x 2 x 7
 */
object Exercise4 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter n: ")
        var n = scanner.nextInt()
        var i = 2
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
