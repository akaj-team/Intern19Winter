package asiantech.internship.summer.kotlin.ex25

import asiantech.internship.summer.kotlin.common.isChainOfSymmetry
import asiantech.internship.summer.kotlin.common.isPrime
import asiantech.internship.summer.kotlin.common.totalOfNumber

object ListPrime2 {

    @JvmStatic
    fun main(args: Array<String>) {
        for (i in 1000000..9999999) {
            val total = i.totalOfNumber()
            if (i.isPrime() && i.toString().isChainOfSymmetry() && total.toString().isChainOfSymmetry()) {
                println(i)
            }
        }
    }

}