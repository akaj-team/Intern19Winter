package asiantech.internship.summer.kotlin

import java.util.Scanner
import java.util.StringTokenizer

object InCount {

    @JvmStatic
    fun main(args: Array<String>) {
        val input = Scanner(System.`in`)
        println("Nhap vao 1 xau: ")
        val str = input.nextLine()
        val strToken = StringTokenizer(str, " ")
        println("So cac tu trong xau la: " + strToken.countTokens())
    }
}
