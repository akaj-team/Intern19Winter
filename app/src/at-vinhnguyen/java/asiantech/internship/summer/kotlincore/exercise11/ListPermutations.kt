package asiantech.internship.summer.exercise11

import inputIntNumber

object ListPermutations {

    @JvmStatic
    fun main(args: Array<String>) {
        print("Nhap n: ")
        val n = inputIntNumber()
        doListPermutations(n)
    }

    private fun doListPermutations(n: Int) {
        val a = IntArray(n)
        for (i in 0 until n) {
            a[i] = i + 1
        }
        for (i in 0 until n) {
            print("" + a[i])
        }
        var i = n - 1
        while (i > 0) {

            if (a[i] > a[i - 1]) {

                for (j in n - 1 downTo i) {
                    if (a[j] > a[i - 1]) {
                        val temp = a[j]
                        a[j] = a[i - 1]
                        a[i - 1] = temp
                        break
                    }
                }

                for (j in n - 1 downTo (n - 1 + i) / 2 + 1) {
                    val temp = a[i + n - 1 - j]
                    a[i + n - 1 - j] = a[j]
                    a[j] = temp
                }

                println("")
                for (j in 0 until n) {
                    print("" + a[j])
                }
                i = n
            }
            i--
        }
    }
}
