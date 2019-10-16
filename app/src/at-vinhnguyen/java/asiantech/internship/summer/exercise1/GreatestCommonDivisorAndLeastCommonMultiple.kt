package exercise1

import inputIntNumber

object GreatestCommonDivisorAndLeastCommonMultiple {
    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap vao a: ")
        val a = inputIntNumber()
        println("Nhap vao b: ")
        val b = inputIntNumber()
        println("Uoc chung lon nhat: \n" + findGreatestCommonDivisor(a, b))
        println("Boi chung nho nhat: \n" + findLeastCommonMultiple(a, b))
    }

    private fun findGreatestCommonDivisor(a: Int, b: Int): Int {
        var a = a
        var b = b
        if (a == 0 || b == 0) {
            return a + b
        }
        while (a != b) {
            if (a > b) {
                a -= b
            } else {
                b -= a
            }
        }
        return a
    }

    private fun findLeastCommonMultiple(a: Int, b: Int): Int {
        val max: Int
        var bcnn = 1
        if (a == 0 || b == 0) {
            println("$a va $b khong co boi chung nho nhat")
        }
        if (a > b) {
            max = a
        } else {
            max = b
        }
        var i = max
        while (true) {
            if (i % a == 0 && i % b == 0) {
                bcnn = i
                break
            }
            i += max
        }
        return bcnn
    }
}
