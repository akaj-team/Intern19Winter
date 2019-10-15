package asiantech.internship.summer.kotlin.ex16

import asiantech.internship.summer.kotlin.common.Common
import java.util.ArrayList

object ShowNumberExist {

    @JvmStatic
    fun main(args: Array<String>) {
        val listNumber = ArrayList<Int>()
        val n = Common.input("Nhập vào độ dài mảng: ")
        for (i in 0 until n) {
            listNumber.add(Common.input("Nhập phần tử thứ $i: "))
        }

        showNumberExist(listNumber)
    }

    private fun showNumberExist(listNumber: List<Int>) {
        for (i in listNumber.indices) {
            if (isExist(listNumber[i], listNumber, i)) {
                println("${listNumber[i]} : " + countNumberExist(listNumber[i], listNumber, i))
            }
        }
    }


    private fun countNumberExist(n: Int, listNumber: List<Int>, index: Int): Int {
        var count = 1
        for (i in index + 1 until listNumber.size) {
            if (n == listNumber[i] && isExist(n, listNumber, index)) {
                count++
            }
        }
        return count
    }

    private fun isExist(n: Int, listNumber: List<Int>, index: Int): Boolean {
        for (i in 0 until index) {
            if (n == listNumber[i]) {
                return false
            }
        }
        return true
    }
}
