package asiantech.internship.summer.kotlin.common

import java.util.*

object Common {
    fun input(text: String): Int {
        val sc = Scanner(System.`in`, "UTF-8")
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
                println("Nhập lại!!")
            }
        } while (check)

        return n
    }

    fun inputString(text: String): String {
        val sc = Scanner(System.`in`, "UTF-8")
        print(text)
        return sc.nextLine()
    }

    fun primeFactor(number: Int): MutableList<String> {
        var num = number
        val listPrime = ArrayList<String>()
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
        val listFibo = ArrayList<Int>()
        var count = 2
        var f1 = 1
        var f2 = 1

        listFibo.add(f1)
        listFibo.add(f2)

        do {
            val fn = f1 + f2
            f1 = f2
            f2 = fn
            listFibo.add(fn)
            count++
        } while (count < n)

        return listFibo
    }

    fun joinString(delimiter: String, arrStr: Array<Any>): String {
        val result = StringBuilder()
        for (i in arrStr.indices) {
            if (i == arrStr.size - 1) {
                result.append(arrStr[i])
            } else {
                result.append(arrStr[i]).append(delimiter)
            }
        }
        return result.toString()
    }
}

fun Int.totalOfNumber(): Int {
    var num = this
    var total = 0

    while (num > 0) {
        total += num % 10
        num /= 10
    }
    return total
}

fun String.isChainOfSymmetry(): Boolean =
    this == StringBuffer(this).reverse().toString()

fun Int.isPrime(): Boolean {
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
