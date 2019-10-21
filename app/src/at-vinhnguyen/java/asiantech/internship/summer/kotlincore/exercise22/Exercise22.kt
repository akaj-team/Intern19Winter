package asiantech.internship.summer.exercise22

import inputIntNumber
import isPrime

object Exercise22 {
    private fun doListDivisor(n: Int) {
        var count = 0
        print("\nCac uoc cua $n la:")
        for (i in 1..n) {
            if (n % i == 0) {
                print(" $i")
                count++
            }
        }
        println("\nCo $count uoc")
    }

    private fun doListDivisorOfPrimeNumber(n: Int) {
        var count = 0
        print("\nCac uoc cua $n la:")
        for (i in 1..n) {
            if (n % i == 0 && i.isPrime()) {
                print(" $i")
                count++
            }
        }
        println("\nCo $count uoc la so nguyen to")

    }

    @JvmStatic
    fun main(args: Array<String>) {
        print("Nhap n")
        val n = inputIntNumber()
        doListDivisor(n)
        doListDivisorOfPrimeNumber(n)
    }

}
