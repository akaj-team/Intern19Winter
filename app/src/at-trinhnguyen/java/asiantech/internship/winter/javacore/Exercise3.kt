package asiantech.internship.winter.javacore

import java.util.*

/*
Bài 3. Hãy viết chương trình tính tổng các chữ số của một số nguyên bất kỳ.
Ví dụ: Số 8545604 có tổng các chữ số là: 8+5+4+5+6+0+4= 32.
 */
object Exercise3 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter n: ")
        val n = scanner.nextInt()
        print("Sum = ${n.sum()}")
    }
}
