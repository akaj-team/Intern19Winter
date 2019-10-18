package asiantech.internship.summer.Kotlin
import java.util.Scanner
import java.util.StringTokenizer
object Standardiz {
    fun New(str: String): String {
        val s: String
        val b: String
        s = str.substring(0, 1)
        b = str.replaceFirst(s.toRegex(), s.toUpperCase())
        return b
    }
    fun equal(strInput: String): String {
        var b = ""
        val strToken = StringTokenizer(strInput, " ,\t,\r")
        b += "" + New(strToken.nextToken())
        while (strToken.hasMoreTokens()) {
            b += " " + New(strToken.nextToken())
        }
        return b
    }

    @JvmStatic
    fun main(args: Array<String>) {

        val input = Scanner(System.`in`)
        println("Nhap vao 1 xau: ")
        val strInput = input.nextLine()
        println("Xau duoc chuan hoa la: " + equal(strInput))
    }
}
