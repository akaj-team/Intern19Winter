package asiantech.internship.summer.kotlincore.exercise1

import asiantech.internship.summer.kotlincore.inputIntNumber

object GreatestCommonDivisorAndLeastCommonMultiple {

    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap vao so dau tien: ")
        val number1 = inputIntNumber()
        println("Nhap vao so thu hai: ")
        val number2 = inputIntNumber()
        println("Uoc chung lon nhat: \n" + findGreatestCommonDivisor(
                number1, number2))
        println("Boi chung nho nhat: \n" + findLeastCommonMultiple(
                number1, number2))
    }

    private fun findGreatestCommonDivisor(a: Int, b: Int): Int {
        var number1 = a
        var number2 = b
        if (number1 == 0 || number2 == 0) {
            return number1 + number2
        }
        while (number1 != number2) {
            if (number1 > number2) {
                number1 -= number2
            } else {
                number2 -= number1
            }
        }
        return number1
    }

    private fun findLeastCommonMultiple(a: Int, b: Int): Int {
        val max: Int = if (a > b) a else b
        val bcnn: Int
        if (a == 0 || b == 0) {
            println("$a va $b khong co boi chung nho nhat")
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
