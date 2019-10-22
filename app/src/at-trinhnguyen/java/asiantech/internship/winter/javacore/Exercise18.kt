package asiantech.internship.winter.javacore

import java.util.*

/*
Bài 18. Nhập một xâu ký tự. Đếm số từ của xâu ký tự đó. Thí dụ " Trường học " có 2 từ.
 */
object Exercise18 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter string: ")
        val str = scanner.nextLine()
        println("Words in string: ${StringTokenizer(str, " ").countTokens()}")
    }
}
