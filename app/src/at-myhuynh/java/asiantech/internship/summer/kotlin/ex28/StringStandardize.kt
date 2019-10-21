package asiantech.internship.summer.kotlin.ex28

import asiantech.internship.summer.kotlin.common.Common
import java.util.*

object StringStandardize {

    @JvmStatic
    fun main(args: Array<String>) {
        var str = Common.inputString("Nhập vào chuỗi: ")
        println(str)
        str = str.trim().toLowerCase(Locale.getDefault())
        val arrStr = str.split("\\s+".toRegex()).toMutableList()

        for (i in arrStr.indices) {
            val temp = arrStr[i][0].toString()
            arrStr[i] = arrStr[i].replaceFirst(temp.toRegex(), temp.toUpperCase(Locale.getDefault()))
        }

        println(arrStr.joinToString(" "))
    }
}
