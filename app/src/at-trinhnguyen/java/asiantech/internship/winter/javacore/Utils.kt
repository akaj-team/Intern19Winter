package asiantech.internship.winter.javacore

import kotlin.math.sqrt

fun Int.isPrimeNumber(): Boolean {
    if (this < 2) return false
    for (i in 2..sqrt(this.toDouble()).toInt()) {
        if (this % i == 0) {
            return false
        }
    }
    return true
}

fun Int.fibonacci(): Int {
    return if (this == 0 || this == 1) 1
    else (this - 1).fibonacci() + (this - 2).fibonacci()
}

fun String.isReversible(): Boolean {
    return this == StringBuffer(this).reversed().toString()
}

const val CHAR_0 = 48
fun Int.sum(): Int {
    return this.toString().sumBy { it.toInt() - CHAR_0 }
}