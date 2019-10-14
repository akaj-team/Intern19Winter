package asiantech.internship.summer.kotlin.ex15

import asiantech.internship.summer.kotlin.common.Common
import java.util.ArrayList

object ListNumberExistTwice {

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
        var count: Int
        for (i in listNumber.indices) {
            count = count(listNumber[i], listNumber, i)
            if (count == 2 && !isExist(listNumber[i], listNum)) {
                listNum.add(listNumber[i])
            }
        }
        return listNum
    }

    private fun isExist(n: Int, listNumber: List<Int>): Boolean {
        for (i in listNumber.indices) {
            if (n == listNumber[i]) {
                return true
            }
        }
        return false
    }

    private fun count(n: Int, listNumber: List<Int>, index: Int): Int {
        var count = 1
        for (i in listNumber.indices) {
            if (i != index) {
                if (n == listNumber[i]) {
                    count++
                }
            }
        }
        return count
    }
}