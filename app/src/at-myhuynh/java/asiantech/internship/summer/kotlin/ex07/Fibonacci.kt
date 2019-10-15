package asiantech.internship.summer.kotlin.ex07

import asiantech.internship.summer.kotlin.common.Common

object Fibonacci {

    @JvmStatic
    fun main(args: Array<String>) {
        val n = Common.input("Nhập vào n: ")
        var count = 2
        var f1 = 1
        var f2 = 1
        var fn: Int

        do {
            fn = f1 + f2
            f1 = f2
            f2 = fn
            count++
        } while (count < n)

        println("Số fibonacci thứ $n: $fn")
    }

}
