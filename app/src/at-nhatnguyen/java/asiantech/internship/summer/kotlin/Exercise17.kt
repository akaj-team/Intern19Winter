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

fun main() {
    print("Nhap so luong phan tu :")
    val n: Int = input()
    var i = 0
    val array = FloatArray(n)
    while (i < n) {
        println("Nhap phan tu thu " + (i + 1) + " ")
        array[i] = inputFloat()
        i++
    }
    println("Sap xep theo thu tu tang dan :")
    array.sort()
    array.forEach(::println)
}
