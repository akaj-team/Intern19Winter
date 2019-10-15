package asiantech.internship.summer.kotlin.ex24

import asiantech.internship.summer.kotlin.common.isChainOfSymmetry
import asiantech.internship.summer.kotlin.common.isPrime

object ListPrime {

    @JvmStatic
    fun main(args: Array<String>) {
        for (i in 10000..9999999) {
            if (i.isPrime() && i.toString().isChainOfSymmetry() && checkItemIsPrime(i)) {
                println(i)
            }
        }

    }

    private fun checkItemIsPrime(n: Int): Boolean {
        val arrNum =
                n.toString().split("").dropWhile(String::isEmpty).dropLastWhile(String::isEmpty)
                        .toTypedArray()
        for (i in arrNum.indices) {
            if (!Integer.parseInt(arrNum[i]).isPrime()) {
                return false
            }
        }
        return true
    }
}
