package asiantech.internship.summer.kotlin.ex09

import asiantech.internship.summer.kotlin.common.Common
import kotlin.math.pow

object Binary {

    @JvmStatic
    fun main(args: Array<String>) {
        val n = Common.input("Nhập vào độ dài n: ")
        val a = IntArray(n)

        var i = 0
        while (i < 2.0.pow(n.toDouble())) {
            generate(a, n, i)
            print(a)
            i++
        }
    }

    private fun generate(a: IntArray, n: Int, index: Int) {
        if (index != 0) {
            a[n - 1]++
        }
        for (i in n - 1 downTo 1) {
            if (a[i] > 1) {
                a[i - 1]++
                a[i] -= 2
            }
        }
    }

    private fun print(a: IntArray) {
        var str = StringBuffer()
        for (i in a.indices) {
            str.append(a[i])
            if (i == a.size - 1) {
                println(str)
                str = StringBuffer()
            }
        }
    }

}
