package asiantech.internship.winter.javacore

import java.util.*

/*
Bài 28. Viết chương trình thực hiện chuẩn hoá một xâu ký tự nhập từ bàn phím (
loại bỏ các dấu cách thừa, chuyển ký tự đầu mỗi từ thành chữ hoa, các ký tự khác thành chữ thường)
 */
object Exercise28 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter string: ")
        val str = scanner.nextLine()
        println(standardized(str))
    }

    private fun firstCharToUpperCase(str: String): String {
        val strOutput = StringBuilder()
        strOutput.append(str.substring(0, 1).toUpperCase(Locale.ENGLISH))
        strOutput.append(str.substring(1).toLowerCase(Locale.ENGLISH))
        return strOutput.toString()
    }

    private fun standardized(strInput: String): String {
        val strOutput = StringBuilder()
        val strToken = StringTokenizer(strInput, " ,\t,\r")
        strOutput.append(firstCharToUpperCase(strToken.nextToken().toString()))
        while (strToken.hasMoreTokens()) {
            strOutput.append(" ").append(firstCharToUpperCase(strToken.nextToken().toString()))
        }
        return strOutput.toString()
    }
}
