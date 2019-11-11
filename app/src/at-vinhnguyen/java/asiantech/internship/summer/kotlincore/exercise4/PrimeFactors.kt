package asiantech.internship.summer.kotlincore.exercise4

import asiantech.internship.summer.kotlincore.doFactorAnalysis
import asiantech.internship.summer.kotlincore.inputIntNumber

object PrimeFactors {

    @JvmStatic
    fun main(args: Array<String>) {
        print("Nhap n:")
        val n = inputIntNumber()
        n.doFactorAnalysis()
    }
}
