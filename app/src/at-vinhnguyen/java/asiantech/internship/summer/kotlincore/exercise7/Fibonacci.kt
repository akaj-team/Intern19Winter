package asiantech.internship.summer.kotlincore.exercise7

import asiantech.internship.summer.kotlincore.getFibonacciInPosition
import asiantech.internship.summer.kotlincore.inputIntNumber

object Fibonacci {

    @JvmStatic
    fun main(args: Array<String>) {
        print("Nhap n:")
        val n = inputIntNumber()
        println("So fibonacci thu " + n + " la: " + n.getFibonacciInPosition())
    }
}
