package asiantech.internship.summer.kotlin.common

import java.util.*

object Common {

    private const val charset = "UTF-8"

    fun input(text: String): Int {
        val sc = Scanner(System.`in`, charset)
        var n = 0
        var check: Boolean

        do {
            try {
                print(text)
                n = Integer.parseInt(sc.nextLine())
                check = false
            } catch (e: Exception) {
                check = true
                println(e.message)
                println("Nhập lại!!!")
            }
        } while (check)

        return n
    }

    fun inputString(text: String): String {
        val sc = Scanner(System.`in`, charset)
        print(text)
        return sc.nextLine()
    }

    fun primeFactor(number: Int): List<String> {
        var num = number
        val listPrime = mutableListOf<String>()
        var index = 2
        while (num >= index) {
            if (num % index == 0) {
                listPrime.add(index.toString())
                num /= index
            } else {
                index++
            }
        }
        return listPrime
    }

    fun getFibonacci(n: Int): List<Int> {
        val listFibo = mutableListOf<Int>()
        var t1 = 0
        var t2 = 1

        for (i in 1..n) {
            listFibo.add(t1)
            val sum = t1 + t2
            t1 = t2
            t2 = sum
        }

        return listFibo
    }
}

fun Int.totalOfNumber() = toString().sumBy { it.toString().toInt() }

fun String.isChainOfSymmetry() = this == this.reversed()

fun Int.isPrime(): Boolean {
    if (this < 1) {
        return false
    }

    if (this == 1 || this == 2) {
        return true
    } else {
        for (i in 2..this / 2) {
            if (this % i == 0) {
                return false
            }
        }
    }
    return true
}

fun Int.getListDiv(): List<Int> {
    val listDiv = ArrayList<Int>()
    for (i in 1..this) {
        if (this % i == 0) {
            listDiv.add(i)
        }
    }
    return listDiv
}
