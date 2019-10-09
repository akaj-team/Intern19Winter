package asiantech.internship.winter.javacore

import java.util.*

/*
Bài 29. Viết chương trình thực hiện nhập một xâu ký tự và tìm từ dài nhất trong xâu đó.
Từ đó xuất hiện ở vị trí nào? (Chú ý. nếu có nhiều từ có độ dài giống nhau thì chọn từ đầu tiên tìm thấy).
 */
object Bai29 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter string: ")
        val str = scanner.nextLine()
        println(findSubstringMaxLength(str))
    }

    private fun findSubstringMaxLength(strInput: String): String {
        var strOutput = ""
        var index = 0
        val strToken = StringTokenizer(strInput, " ,\t,\r")
        for (i in 0 until strToken.countTokens()) {
            val strTemp = strToken.nextToken()
            if (strOutput.length < strTemp.length)
                strOutput = strTemp
            index = i
        }
        while (strToken.hasMoreTokens()) {
            if (strOutput.length < strToken.nextToken().length)
                strOutput = strToken.toString()

        }
        return "Substring max length: $strOutput , index: $index"
    }
}