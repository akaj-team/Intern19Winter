package asiantech.internship.summer.kotlin

import java.util.Scanner
import java.util.StringTokenizer

object LongWord {

    private fun searchMax(strInput: String) {
        val strToken = StringTokenizer(strInput, " ,\t,\r")
        var max: Int
        var i = 1
        var lengthStr: Int
        max = strToken.nextToken().length
        var inMax = i
        while (strToken.hasMoreTokens()) {
            lengthStr = strToken.nextToken().length
            i++
            if (max < lengthStr) {
                max = lengthStr
                inMax = i
            }
        }
        println("Do dai xau lon nhat la: $max o vi tri $inMax")
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val input = Scanner(System.`in`)
        println("Nhap vao 1 xau: ")
        val strInput = input.nextLine()
        searchMax(strInput)
    }
}
