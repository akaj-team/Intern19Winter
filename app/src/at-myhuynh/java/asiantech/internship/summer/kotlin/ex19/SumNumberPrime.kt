package asiantech.internship.summer.kotlin.ex19

import asiantech.internship.summer.kotlin.common.Common
import asiantech.internship.summer.kotlin.common.isPrime
import asiantech.internship.summer.kotlin.common.totalOfNumber

object SumNumberPrime {
    @JvmStatic
    fun main(args: Array<String>) {
        val n = Common.input("Nhập vào số n:")
        (10000..99999).filter { it.isPrime() && it.totalOfNumber() == n }.forEach(::println)
    }
}
