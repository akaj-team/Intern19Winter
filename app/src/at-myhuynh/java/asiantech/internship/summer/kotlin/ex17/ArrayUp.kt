package asiantech.internship.summer.kotlin.ex17

import asiantech.internship.summer.kotlin.common.Common
import java.util.*

object ArrayUp {

    @JvmStatic
    fun main(args: Array<String>) {
        val n = Common.input("Nhập số lượng phần tử:")
        val arrInt = IntArray(n)
        for (i in 0 until n) {
            arrInt[i] = Common.input("Nhập phần tử thứ $i: ")
        }

        Arrays.sort(arrInt)
        for (i in arrInt) {
            println(i)
        }

    }
}
