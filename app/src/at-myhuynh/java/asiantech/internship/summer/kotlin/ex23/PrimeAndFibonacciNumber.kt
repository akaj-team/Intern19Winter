package asiantech.internship.summer.kotlin.ex23

import asiantech.internship.summer.kotlin.common.Common
import asiantech.internship.summer.kotlin.common.isPrime
import java.util.ArrayList

object PrimeAndFibonacciNumber {

    @JvmStatic
    fun main(args: Array<String>) {
        val n = Common.input("Nhập vào n: ")

        println("$n số nguyên tố đầu tiên: " + getListPrime(n))
        println("$n số Fibo đầu tiên: " + Common.getFibonacci(n))
    }

    private fun getListPrime(n: Int): List<Int> {
        val listPrime = ArrayList<Int>()
        var count = 1
        var index = 1
        while (count <= n) {
            if (index.isPrime()) {
                listPrime.add(index)
                count++
            }
            index++
        }
        return listPrime
    }

}
