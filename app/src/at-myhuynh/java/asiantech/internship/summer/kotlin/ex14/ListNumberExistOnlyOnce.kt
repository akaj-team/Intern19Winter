package asiantech.internship.summer.kotlin.ex14

import asiantech.internship.summer.kotlin.common.Common
import java.util.ArrayList

object ListNumberExistOnlyOnce {

    @JvmStatic
    fun main(args: Array<String>) {
        val listNumber = ArrayList<Int>()
        val n = Common.input("Nhập vào độ dài mảng: ")
        for (i in 0 until n) {
            listNumber.add(Common.input("Nhập phần tử thứ $i: "))
        }

        println(getNumbers(listNumber))
    }

    private fun getNumbers(listNumber: List<Int>): List<Int> {
        val listNum = ArrayList<Int>()
        for (i in listNumber.indices) {
            if (isExist(listNumber[i], listNumber, i)) {
                listNum.add(listNumber[i])
            }
        }
        return listNum
    }

    private fun isExist(n: Int, listNumber: List<Int>, index: Int): Boolean {
        for (i in listNumber.indices) {
            if (i != index) {
                if (n == listNumber[i]) {
                    return false
                }
            }
        }
        return true
    }
}
