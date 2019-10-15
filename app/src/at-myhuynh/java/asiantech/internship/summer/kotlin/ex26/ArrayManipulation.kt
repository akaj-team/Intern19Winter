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

        val maxNumbers = findMaxNumber(listNumber)
        val secondMaxNumber = findMaxNumberSecond(listNumber, maxNumbers[0])

        println("Max number: " + maxNumbers[0] + " - " + maxNumbers[1])
        println("Second max number: " + secondMaxNumber[0] + " - " + secondMaxNumber[1])

        listNumber.sort()
        listNumber.reverse()
        println(listNumber)

        insertEle(listNumber, 3)
        println(listNumber)
    }

    private fun findMaxNumber(listNumber: List<Int>): IntArray {
        val numbers = intArrayOf(0, 0)
        for (i in listNumber.indices) {
            if (numbers[0] < listNumber[i]) {
                numbers[0] = listNumber[i]
                numbers[1] = i
            }
        }
        return numbers
    }

    private fun findMaxNumberSecond(listNumber: List<Int>, maxNumber: Int): IntArray {
        val numbers = intArrayOf(0, 0)
        for (i in listNumber.indices) {
            val n = listNumber[i]
            if (numbers[0] < n && n != maxNumber) {
                numbers[0] = n
                numbers[1] = i
            }
        }
        return numbers
    }

    private fun insertEle(listNumber: MutableList<Int>, n: Int) {
        listNumber.add(n)
        listNumber.sort()
        listNumber.reverse()
    }

}
