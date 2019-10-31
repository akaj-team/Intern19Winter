package asiantech.internship.summer.kotlincore.exercise2

import asiantech.internship.summer.kotlincore.inputIntNumber

object BaseNumber {

    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap he co so ban can chuyen: ")
        val coSo = inputIntNumber()
        println("Nhap so o he thap phan ban can chuyen: ")
        val so = inputIntNumber()
        println("So sau khi chuyen: " + convertBaseNumber(
                coSo, so))
    }

    private fun convertBaseNumber(baseNumber: Int, number: Int): Int {
        var a = number
        var i = 1
        var result = 0
        while (a > 1) {
            result += a % baseNumber * i
            a /= baseNumber
            i *= 10
        }
        return result
    }
}
