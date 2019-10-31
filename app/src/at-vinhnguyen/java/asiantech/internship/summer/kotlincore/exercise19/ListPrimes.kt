package asiantech.internship.summer.kotlincore.exercise19

import asiantech.internship.summer.kotlincore.inputIntNumber
import asiantech.internship.summer.kotlincore.isPrime
import asiantech.internship.summer.kotlincore.sumOfNumber

object ListPrimes {

    @JvmStatic
    fun main(args: Array<String>) {
        val n = inputIntNumber()
        println("Liệt kê tất cả số nguyên tố có 5 chữ số có tổng các chữ số bằng: $n")
        var i = 10001
        while (i < 99999) {
            if (i.isPrime()) {
                if (n == i.sumOfNumber()) {
                    println(i)
                }
            }
            i += 2
        }
    }
}
