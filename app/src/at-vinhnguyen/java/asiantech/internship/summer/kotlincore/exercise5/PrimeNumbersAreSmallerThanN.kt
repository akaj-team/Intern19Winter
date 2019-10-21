package asiantech.internship.summer.kotlincore.exercise5

import inputIntNumber
import isPrime

object PrimeNumbersAreSmallerThanN {

    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap n:")
        val n = inputIntNumber()
        println("Cac so nguyen to nho hon n bao gom: " + findPrimeNumbersAreSmallerThanN(
            n))
    }

    private fun findPrimeNumbersAreSmallerThanN(n: Int): String {
        val builder = StringBuilder()
        for (i in 2 until n) {
            if (i.isPrime()) {
                builder.append(i).append(" ")
            }
        }
        return builder.toString()
    }
}
