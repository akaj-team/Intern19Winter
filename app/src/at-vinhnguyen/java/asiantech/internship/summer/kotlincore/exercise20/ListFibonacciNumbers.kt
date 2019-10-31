package asiantech.internship.summer.kotlincore.exercise20

import asiantech.internship.summer.kotlincore.getFibonacciInPosition
import asiantech.internship.summer.kotlincore.inputIntNumber
import asiantech.internship.summer.kotlincore.isPrime

object ListFibonacciNumbers {

    @JvmStatic
    fun main(args: Array<String>) {
        print("Nhập số tự nhiên n = ")
        val n = inputIntNumber()
        print(
                "Các số fibonacci nhỏ hơn " + n + " và "
                        + "là số nguyên tố: "
        )
        var i = 0
        while (i.getFibonacciInPosition() < n) {
            val fi = i.getFibonacciInPosition()
            if (fi.isPrime()) {
                print("$fi ")
            }
            i++
        }
    }
}
