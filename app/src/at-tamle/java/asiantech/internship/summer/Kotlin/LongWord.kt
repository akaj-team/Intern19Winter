package asiantech.internship.summer.Kotlin
import java.util.Scanner
import java.util.StringTokenizer
object LongWord {

    fun searchMax(strInput: String) {
        val strToken = StringTokenizer(strInput, " ,\t,\r")
        var Max: Int
        var i = 1
        var lengthStr: Int
        Max = strToken.nextToken().length
        var inMax = i
        while (strToken.hasMoreTokens()) {
            lengthStr = strToken.nextToken().length
            i++
            if (Max < lengthStr) {
                Max = lengthStr
                inMax = i
            }
        }
        println("Do dai xau lon nhat la: $Max o vi tri $inMax")
    }
    @JvmStatic
    fun main(args: Array<String>) {
        val input = Scanner(System.`in`)
        println("Nhap vao 1 xau: ")
        val strInput = input.nextLine()
        searchMax(strInput)
    }
}
