package asiantech.internship.summer.exercise9

import inputIntNumber

object ListsBinaryStrings {

    @JvmStatic
    fun main(args: Array<String>) {
        print("Nhap n: ")
        val n = inputIntNumber()
        doListsBinaryStrings(n)
    }

    private fun doListsBinaryStrings(n: Int) {
        val a = IntArray(n)
        for (i in 0 until n) {
            a[i] = 0
        }
        for (i in a.indices) {
            print(+a[i])
        }
        var i = n - 1
        while (i >= 0) {
            if (a[i] == 0) {
                a[i] = 1
                for (j in i + 1 until n) {
                    a[j] = 0
                }
                for (j in 0 until n) {
                    print(a[j])
                }
                println("\n")
            }
            i = n
            i--
        }
    }
}
