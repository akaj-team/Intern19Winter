package asiantech.internship.summer.kotlin.ex10

import asiantech.internship.summer.kotlin.common.Common

object Subset {

    @JvmStatic
    fun main(args: Array<String>) {
        val n = Common.input("Nhập n: ")
        val k = Common.input("Nhập k: ")
        val array = IntArray(n)

        backTracking(array, n, k, 1)
    }

    private fun backTracking(a: IntArray, n: Int, k: Int, i: Int) {
        var j: Int = a[i - 1] + 1
        while (j <= n - k + i) {
            a[i] = j
            if (i == k) {
                val str = StringBuilder()
                for (temp in 1..k) {
                    str.append(a[temp])
                    if (temp == k) {
                        println(str)
                    }
                }
            } else {
                backTracking(a, n, k, i + 1)
            }
            j++
        }
    }
}