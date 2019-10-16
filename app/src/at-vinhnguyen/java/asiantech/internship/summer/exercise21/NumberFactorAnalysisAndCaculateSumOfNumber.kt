package asiantech.internship.summer.exercise21

import doFactorAnalysis
import inputIntNumber
import sumOfNumber

object NumberFactorAnalysisAndCaculateSumOfNumber {

    @JvmStatic
    fun main(args: Array<String>) {
        print("Nhap n")
        val n = inputIntNumber()
        print("n = 1.")
        n.doFactorAnalysis()
        println("Tong cac chu so cua " + n + " la: " + n.sumOfNumber())
    }
}
