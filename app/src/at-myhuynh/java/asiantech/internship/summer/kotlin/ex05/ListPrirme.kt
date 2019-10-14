package asiantech.internship.summer.kotlin.ex05

import asiantech.internship.summer.kotlin.common.Common
import asiantech.internship.summer.kotlin.common.isPrime

object ListPrime {

    @JvmStatic
    fun main(args: Array<String>) {
        val n = Common.input("Nhập vào số n: ")
        print(n)
    }

    private fun print(number: Int) {
        for (i in 1..number) {
            if (i.isPrime()) {
                println(i)
            }
        }
    }
}