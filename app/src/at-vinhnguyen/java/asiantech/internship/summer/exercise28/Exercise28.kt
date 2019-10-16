package exercise28

import java.util.*

object Exercise28 {

    private fun standardized(string: String): String {
        var str = string
        str = str.trim { it <= ' ' }
        str = str.replace("\\s+".toRegex(), " ")
        return str
    }

    private fun standardizedProperNoun(string: String): String {
        var str = string
        str = standardized(str)
        val temp = str.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        str = ""
        for (i in temp.indices) {
            str += temp[i][0].toString().toUpperCase() + temp[i].substring(1)
            if (i < temp.size - 1) {
                str += " "
            }
        }
        return str
    }

    @JvmStatic
    fun main(sgr: Array<String>) {
        print("Nhap xau ban can chuan hoa: ")
        var str = Scanner(System.`in`).nextLine()
        str = standardizedProperNoun(str)
        println(str)
    }
}
