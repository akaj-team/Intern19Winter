package asiantech.internship.winter.javacore

import java.util.*

/*
Bài 7. Dãy số Fibonacci được định nghĩa như sau:
 F0 =1, F1 = 1; Fn = Fn-1 + Fn-2 với n>=2. Hãy viết chương trình tìm số Fibonacci thứ n.
 */
object Exercise7 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter n: ")
        val n = scanner.nextInt()
        print("F($n) = ${n.fibonacci()}")
    }
}
