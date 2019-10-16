package exercise17

import inputIntNumber
import java.util.*

object Excersice17 {

    fun inputFloatNumber(): Float = Scanner(System.`in`).nextLine().toFloat()

    fun minFloatPosition(a: FloatArray, n: Int): Int {
        var min = a[0]
        var key = 0
        for (j in 0 until n) {
            if (min > a[j]) {
                min = a[j]
                key = j
            }
        }
        return key
    }

    fun maxFloat(a: FloatArray, n: Int): Float {
        var max = a[0]
        for (j in 0 until n) {
            if (max < a[j]) {
                max = a[j]
            }
        }
        return max
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val n: Int
        var i: Int
        print("Nhap n= ")
        n = inputIntNumber()
        val array = FloatArray(n)
        i = 0
        while (i < n) {
            println("Nhap phan tu thu " + (i + 1) + " ")
            array[i] = inputFloatNumber()
            i++
        }
        i = 0
        println("Sap xep theo thu tu tang dan")
        while (i < n) {
            println(" " + array[minFloatPosition(array, n)])
            array[minFloatPosition(array, n)] = maxFloat(array, n)
            i++
        }
    }
}
