package asiantech.internship.summer.kotlin.ex06

import asiantech.internship.summer.kotlin.common.Common
import asiantech.internship.summer.kotlin.common.isPrime

object ListPrimeFirst {

    @JvmStatic
    fun main(args: Array<String>) {
        val n = Common.input("Nhập vào n: ")
        print(n)
    }

    private fun print(n: Int) {
        var i = n
        var number = 1

        do {
            if (number.isPrime()) {
                i--
                println(number)
            }
            number++
        } while (i > 0)
    }
}
