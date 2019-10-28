package asiantech.internship.summer.kotlin.ex20

import asiantech.internship.summer.kotlin.common.Common
import asiantech.internship.summer.kotlin.common.isPrime

object FibonacciPrime {
    @JvmStatic
    fun main(args: Array<String>) {
        val n = Common.input("Nhập vào số n:")
        getListFibonacciLessThenN(n).filter(Int::isPrime).forEach(::println)
    }

    private fun getListFibonacciLessThenN(n: Int): MutableList<Int> {
        val listFibonacci = mutableListOf<Int>()
        var t1 = 0
        var t2 = 1
        while (t1 <= n) {
            listFibonacci.add(t1)
            val sum = t1 + t2
            t1 = t2
            t2 = sum
        }

        return listFibonacci
    }
}
