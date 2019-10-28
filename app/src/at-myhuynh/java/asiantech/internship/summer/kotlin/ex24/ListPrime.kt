package asiantech.internship.summer.kotlin.ex24

import asiantech.internship.summer.kotlin.common.isChainOfSymmetry
import asiantech.internship.summer.kotlin.common.isPrime

object ListPrime {

    @JvmStatic
    fun main(args: Array<String>) {
        (10000..9999999)
                .filter { it.isPrime() && it.toString().isChainOfSymmetry() && checkItemIsPrime(it) }
                .forEach(::println)
    }

    private fun checkItemIsPrime(n: Int): Boolean {
        val arrNum = n.toString().split("\\s+".toRegex())
        for (i in arrNum.indices) {
            if (!arrNum[i].toInt().isPrime()) {
                return false
            }
        }
        return true
    }
}
