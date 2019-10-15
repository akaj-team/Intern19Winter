package asiantech.internship.summer.kotlin.ex21

import asiantech.internship.summer.kotlin.common.Common
import asiantech.internship.summer.kotlin.common.isPrime
import asiantech.internship.summer.kotlin.common.totalOfNumber

object SumAndPrime {

    @JvmStatic
    fun main(args: Array<String>) {
        val n = Common.input("Nhập vào 1 số:")
        println("Total: " + n.totalOfNumber())

        var strPrime: MutableList<String> = mutableListOf()
        if (n.isPrime()) {
            strPrime.add(n.toString())
        } else {
            strPrime = Common.primeFactor(n)
        }
        println("$n => " + Common.joinString("x", strPrime.toTypedArray()))
    }
}
