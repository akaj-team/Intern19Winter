package asiantech.internship.summer.kotlin

import java.util.Scanner
import java.util.StringTokenizer

fun main() {
    val scan = Scanner(System.`in`)
    print("Nhap vao 1 xau: ")
    val str = scan.nextLine()
    val strToken = StringTokenizer(str, " ")
    println("So tu trong xau la: " + strToken.countTokens())
}
