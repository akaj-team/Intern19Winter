package asiantech.internship.summer.kotlincore.exercise14

import asiantech.internship.summer.kotlincore.inputIntNumber

object ListElementsThatAppearOnly {

    private fun countElement(a: IntArray, n: Int, i: Int): Int {
        var count = 0
        for (j in 0 until n) {
            if (a[j] == i) {
                count++
            }
        }
        return count
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val n = inputIntNumber()
        var i = 0
        val array = IntArray(n)
        while (i < n) {
            print("Nhap phan tu thu " + (i + 1) + " ")
            array[i] = inputIntNumber()
            i++
        }
        print("Cac phan tu trong day xuat hien 1 lan: ")
        i = 0
        while (i < n) {
            if (countElement(
                            array, n, array[i]) == 1) {
                print("  " + array[i])
            }
            i++
        }
    }
}
