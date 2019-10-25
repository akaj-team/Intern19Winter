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

        listNumber.groupBy { it }.filter { it.value.size == 2 }.forEach(::println)
    }
}
