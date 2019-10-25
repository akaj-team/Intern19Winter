package asiantech.internship.summer.kotlin.ex29

import asiantech.internship.summer.kotlin.common.Common

object FindWord {

    @JvmStatic
    fun main(args: Array<String>) {
        var str = Common.inputString("Nhập vào chuỗi kí tự:")
        str = str.replace("\\s+".toRegex(), " ")
        val arrWords = str.split("[ ]+".toRegex()).dropLastWhile { it.isEmpty() }

        arrWords.forEach(::println)

        val maxLengthWord = intArrayOf(arrWords.max()?.length!!, arrWords.indexOf(arrWords.max()!!))
        println(
                "Từ: " + arrWords[maxLengthWord[1]]
                        + "\nVị trí: " + (maxLengthWord[1] + 1)
                        + "\nĐộ dài: " + maxLengthWord[0]
        )
    }
}
