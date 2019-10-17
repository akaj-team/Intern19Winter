package asiantech.internship.summer.kotlin
import java.util.Scanner
private fun inputFloat(): Float {
    val input = Scanner(System.`in`)
    var check = false
    var n = 0f
    while (!check) {
        print(" ")
        try {
            n = input.nextInt().toFloat()
            check = true
        } catch (e: Exception) {
            println("Ban phai nhap so! hay nhap lai...")
            input.nextLine()
        }

    }
    return n
}

private fun minFloat(a: FloatArray, n: Int): Int {
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

private fun maxFloat(a: FloatArray, n: Int): Float {
    var max = a[0]
    for (j in 0 until n) {
        if (max < a[j]) max = a[j]
    }
    return max
}

fun main() {
    val shareds = Shared()
    val n: Int = shareds.input()
    var i = 0
    val array = FloatArray(n)
    while (i < n) {
        println("Nhap phan tu thu " + (i + 1) + " ")
        array[i] = inputFloat()
        i++
    }
    i = 0
    println("Sap xep theo thu tu tang dan")
    while (i < n) {
        println(" " + array[minFloat(array, n)])
        array[minFloat(array, n)] = maxFloat(array, n)
        i++
    }
}
