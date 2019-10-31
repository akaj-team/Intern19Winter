package asiantech.internship.summer.kotlin.ex18

import asiantech.internship.summer.kotlin.common.Common

object CountWord {

    @JvmStatic
    fun main(args: Array<String>) {
        val strWord = Common.inputString("Nhập vào chuỗi: ")
        val count = strWord.split("\\s+".toRegex()).dropLastWhile { it.isEmpty() }.size
        println("'$strWord' có $count từ.")
    }

}
