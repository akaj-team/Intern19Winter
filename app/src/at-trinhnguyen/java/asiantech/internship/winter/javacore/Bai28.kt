package asiantech.internship.winter.javacore

import java.util.*
import java.util.StringTokenizer

/*
Bài 28. Viết chương trình thực hiện chuẩn hoá một xâu ký tự nhập từ bàn phím (
loại bỏ các dấu cách thừa, chuyển ký tự đầu mỗi từ thành chữ hoa, các ký tự khác thành chữ thường)
 */
object Bai28 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter string: ")
        val str = scanner.nextLine()
        println(standardized(str))
    }

    private fun firstCharToUpperCase(str: String): String {
        val s: String = str.substring(0, 1)
        val strOutput: String
        strOutput = str.replaceFirst(s.toRegex(), s.toUpperCase())
        return strOutput
    }

    private fun standardized(strInput: String): String {
        var strOutput = ""
        val strToken = StringTokenizer(strInput, " ,\t,\r")
        strOutput += "" + firstCharToUpperCase(strToken.nextToken())
        while (strToken.hasMoreTokens()) {
            strOutput += " " + firstCharToUpperCase(strToken.nextToken())
        }
        return strOutput
    }
}