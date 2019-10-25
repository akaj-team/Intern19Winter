package asiantech.internship.summer.kotlin.ex26

import asiantech.internship.summer.kotlin.common.Common
import java.util.*

object ArrayManipulation {

    @JvmStatic
    fun main(args: Array<String>) {
        val listNumber = ArrayList<Int>()
        val n = Common.input("Nhập vào độ dài mảng: ")

        for (i in 0 until n) {
            listNumber.add(Common.input("Nhập phần tử thứ $i: "))
        }

        println(listNumber)

        val maxNumber = listNumber.max()!!
        println("Max number: " + maxNumber + " - " + listNumber.indexOf(maxNumber))

        val secondMaxNumber = findMaxNumberSecond(listNumber, maxNumber)
        println("Second max number: " + secondMaxNumber + " - " + listNumber.indexOf(secondMaxNumber))

        listNumber.sortDescending()
        println(listNumber)

        insertEle(listNumber, 3)
        println(listNumber)
    }

    private fun findMaxNumberSecond(listNumber: List<Int>, maxNumber: Int): Int? {
        return listNumber.maxBy {
            if (it == maxNumber) {
                0
            } else {
                it
            }
        }
    }

    private fun insertEle(listNumber: MutableList<Int>, n: Int) {
        listNumber.add(listNumber.indexOfFirst { it < n }, n)
    }

}
