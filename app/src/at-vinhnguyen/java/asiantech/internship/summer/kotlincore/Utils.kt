package asiantech.internship.summer.kotlincore

import java.util.*

fun Int.isPrime(): Boolean {
    for (j in 2 until this / 2) {
        if (this % j == 0) {
            return false
        }
    }
    return true
}

fun Int.sumOfNumber(): Int {
    var n = this
    var sum = 0
    while (n > 0) {
        sum += n % 10
        n /= 10
    }
    return sum
}

fun Int.doFactorAnalysis() {
    var n = this
    var i = 2
    while (n > 1) {
        if (i.isPrime()) {
            if (n % i == 0) {
                print("$i ")
                n /= i
            } else {
                i++
            }
        } else {
            i++
        }
    }
}

fun Int.isReversible(): Boolean {
    val str = this.toString()
    val strBuilder = StringBuilder(str)
    val check = "" + strBuilder.reverse()
    return str == check
}

fun IntArray.maxPosition(n: Int): Int {
    var max = this[0]
    var key = 0
    for (j in 0 until n) {
        if (max < this[j]) {
            max = this[j]
            key = j
        }
    }
    return key
}

fun IntArray.maxPositionAfter(n: Int): Int {
    var i = 0
    var key = 0
    var max = 0
    while (i < n) {
        if (this[i] > max && this[i] != this[this.maxPosition(n)]) {
            max = this[i]
            key = i
        }
        i++
    }
    return key
}

fun IntArray.printArray(begin: Int, end: Int) {
    println()
    var i: Int = begin
    while (i < end) {
        print(" " + this[i])
        i++
    }
    println()
}

fun IntArray.addElement(pt: Int) {
    this[0] = pt
    Arrays.sort(this)
}

fun Int.getFibonacciInPosition(): Int {
    return if (this <= 1) {
        this
    } else (this - 1).getFibonacciInPosition() + (this - 2).getFibonacciInPosition()
}

fun inputIntNumber(): Int {
    val input = Scanner(System.`in`)
    var check = false
    var n = 0
    while (!check) {
        print(" ")
        try {
            n = Integer.parseInt(input.nextLine())
            check = true
        } catch (e: Exception) {
            println("Ban phai nhap so! hay nhap lai...")
        }
    }
    return n
}
