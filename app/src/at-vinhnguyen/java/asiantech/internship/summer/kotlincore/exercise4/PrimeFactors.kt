package asiantech.internship.summer.exercise4

import doFactorAnalysis
import inputIntNumber

object PrimeFactors {

    @JvmStatic
    fun main(args: Array<String>) {
        print("Nhap n:")
        val n = inputIntNumber()
        n.doFactorAnalysis()
    }
}
