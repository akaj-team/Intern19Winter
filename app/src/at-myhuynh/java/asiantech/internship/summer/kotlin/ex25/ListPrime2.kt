package asiantech.internship.summer.kotlin.ex25

import asiantech.internship.summer.kotlin.common.isChainOfSymmetry
import asiantech.internship.summer.kotlin.common.isPrime
import asiantech.internship.summer.kotlin.common.totalOfNumber

object ListPrime2 {

    @JvmStatic
    fun main(args: Array<String>) {
        (1000000..9999999)
                .filter {
                    it.isPrime()
                            && it.toString().isChainOfSymmetry()
                            && it.totalOfNumber().toString().isChainOfSymmetry()
                }
                .forEach(::println)
    }
}
