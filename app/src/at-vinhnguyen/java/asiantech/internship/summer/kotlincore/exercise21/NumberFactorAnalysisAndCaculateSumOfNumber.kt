package asiantech.internship.summer.kotlincore.exercise21

import asiantech.internship.summer.kotlincore.doFactorAnalysis
import asiantech.internship.summer.kotlincore.inputIntNumber
import asiantech.internship.summer.kotlincore.sumOfNumber

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
