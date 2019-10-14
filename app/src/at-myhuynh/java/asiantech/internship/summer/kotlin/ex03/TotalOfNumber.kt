package asiantech.internship.summer.kotlin.ex03

import asiantech.internship.summer.kotlin.common.Common
import asiantech.internship.summer.kotlin.common.totalOfNumber

object TotalOfNumber {

    @JvmStatic
    fun main(args: Array<String>) {
        val number = Common.input("Nhập vào số cần tính: ")
        val total = number.totalOfNumber()

        println("$number => $total")
    }
}