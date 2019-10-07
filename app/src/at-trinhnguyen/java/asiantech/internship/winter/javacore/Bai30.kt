package com.congtrinh

import java.util.*

/*
Bài 30.Viết chương trình thực hiện nhập một xâu họ tên theo cấu trúc:
họ...đệm...tên; chuyển xâu đó sang biểu diễn theo cấu trúc tên…họ…đệm.
 */
object Bai30 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter string: ")
        val str = scanner.nextLine()
        println(flipLastAndFirstName(str))
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

    private fun flipLastAndFirstName(strInput:String):String{
        val str= standardized(strInput)
        val strToken=StringTokenizer(str," ")
        var lastName=""
        val firstName: String
        for(i in 0 until (strToken.countTokens()-1)){
            lastName+=" "+strToken.nextToken()
        }
        firstName=strToken.nextToken()
        return firstName+lastName
    }
}