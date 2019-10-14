package asiantech.internship.summer.kotlin.ex04

import asiantech.internship.summer.kotlin.common.Common

object PrimeFactor {

    @JvmStatic
    fun main(args: Array<String>) {
        val number = Common.input("Nhập vào n: ")
        val listPrime = Common.primeFactor(number)
        println(listPrime.joinToString(
            separator = "x",
            prefix = "",
            postfix = "",
            limit = listPrime.size,
            truncated = ""
        ))
    }
}
