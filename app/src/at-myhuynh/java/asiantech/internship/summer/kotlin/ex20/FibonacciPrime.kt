package asiantech.internship.summer.kotlin.ex20

import asiantech.internship.summer.kotlin.common.Common
import asiantech.internship.summer.kotlin.common.isPrime

object FibonacciPrime {
    @JvmStatic
    fun main(args: Array<String>) {
        val n = Common.input("Nhập vào số n:")
        showFibonacciIsPrime(n)
    }

    private fun showFibonacciIsPrime(n: Int) {
        var f1 = 1
        var f2 = 1
        var fn = 1

        do {
            if (fn < n && fn.isPrime()) {
                println(fn)
            }
            fn = f1 + f2
            f1 = f2
            f2 = fn
        } while (fn < n)
    }
}
