package asiantech.internship.summer.kotlin.ex11

import asiantech.internship.summer.kotlin.common.Common

object Permutation {

    @JvmStatic
    fun main(args: Array<String>) {
        val n = Common.input("Nhập vào số n: ")
        val arrInt = IntArray(n)

        initArr(arrInt, n)
        listPermutation(arrInt)

    }

    private fun initArr(arrInt: IntArray, n: Int) {
        for (i in 0 until n) {
            arrInt[i] = i + 1
        }
    }

    private fun listPermutation(arrInt: IntArray) {
        val n = arrInt.size
        var str = StringBuffer()

        for (i in 0 until n) {
            str.append(arrInt[i])
            if (i == n - 1) {
                println(str)
            }
        }

        var i = n - 1
        while (i > 0) {
            if (arrInt[i] >= arrInt[i - 1]) {
                for (j in n - 1 downTo i) {
                    if (arrInt[j] > arrInt[i - 1]) {
                        val temp = arrInt[j]
                        arrInt[j] = arrInt[i - 1]
                        arrInt[i - 1] = temp
                        break
                    }
                }

                for (j in n - 1 downTo (n - 1 + i) / 2 + 1) {
                    val temp = arrInt[i + n - 1 - j]
                    arrInt[i + n - 1 - j] = arrInt[j]
                    arrInt[j] = temp
                }

                str = StringBuffer()
                for (j in 0 until n) {
                    str.append(arrInt[j])
                    if (j == n - 1) {
                        println(str)
                    }
                }
                i = n
            }
            i--
        }
    }
}
