package asiantech.internship.summer.kotlin.ex05

import asiantech.internship.summer.kotlin.common.Common
import asiantech.internship.summer.kotlin.common.isPrime

object ListPrime {

    @JvmStatic
    fun main(args: Array<String>) {
        val n = Common.input("Nhập vào số n: ")
        (1..n).filter(Int::isPrime).forEach(::println)
    }
}
