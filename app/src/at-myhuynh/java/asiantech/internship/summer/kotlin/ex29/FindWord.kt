package asiantech.internship.summer.kotlin.ex29

import asiantech.internship.summer.kotlin.common.Common

object FindWord {

    @JvmStatic
    fun main(args: Array<String>) {
        var str = Common.inputString("Nhập vào chuỗi kí tự:")
        str = str.replace("\\s".toRegex(), " ")
        val arrWords = str.split("[ ]+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        val maxLengthWord = getMaxLengthWord(arrWords)
        println(
                "Từ: " + arrWords[maxLengthWord[1]]
                        + "\nVị trí: " + (maxLengthWord[1] + 1)
                        + "\nĐộ dài: " + maxLengthWord[0]
        )
    }


    private fun getMaxLengthWord(arrWords: Array<String>): IntArray {
        val arr = intArrayOf(0, 0)
        for (i in arrWords.indices) {
            if (arr[0] < arrWords[i].length) {
                arr[0] = arrWords[i].length
                arr[1] = i
            }
        }
        return arr
    }

}
