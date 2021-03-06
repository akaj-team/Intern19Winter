package asiantech.internship.summer.kotlincore.exercise6

import asiantech.internship.summer.kotlincore.inputIntNumber
import asiantech.internship.summer.kotlincore.isPrime

object PrimesLessThanN {

    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap so luong so nguyen to: ")
        val n = inputIntNumber()
        println(showPrimes(n))
    }

    private fun showPrimes(n: Int): String {
        val builder = StringBuilder()
        var count = 0
        var j = 2
        while (count < n) {
            if (j.isPrime()) {
                count++
                builder.append(" ").append(j)
            }
            j += 2
        }
        return builder.toString()
    }
}
