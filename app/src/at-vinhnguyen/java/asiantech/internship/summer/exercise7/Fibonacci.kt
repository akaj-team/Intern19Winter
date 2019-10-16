package exercise7

import getFibonacciInPosition
import inputIntNumber

object Fibonacci {

    @JvmStatic
    fun main(args: Array<String>) {
        print("Nhap n:")
        val n = inputIntNumber()
        println("So fibonacci thu " + n + " la: " + n.getFibonacciInPosition())
    }
}
