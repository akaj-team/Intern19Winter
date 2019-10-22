package asiantech.internship.winter.javacore

import java.util.*

/*
Bài 30.Viết chương trình thực hiện nhập một xâu họ tên theo cấu trúc:
họ...đệm...tên; chuyển xâu đó sang biểu diễn theo cấu trúc tên…họ…đệm.
 */
object Exercise30 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter string: ")
        val str = scanner.nextLine()
        println(flipLastAndFirstName(str))
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

    private fun flipLastAndFirstName(strInput: String): String {
        val str = standardized(strInput)
        val strToken = StringTokenizer(str, " ")
        var lastName = ""
        val firstName: String
        for (i in 0 until (strToken.countTokens() - 1)) {
            lastName += " " + strToken.nextToken()
        }
        firstName = strToken.nextToken()
        return firstName + lastName
    }
}
