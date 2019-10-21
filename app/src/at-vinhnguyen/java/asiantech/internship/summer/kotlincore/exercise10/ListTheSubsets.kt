package asiantech.internship.summer.kotlincore.exercise10

import inputIntNumber

object ListTheSubsets {

    @JvmStatic
    fun main(args: Array<String>) {
        print("Nhap n: ")
        val n = inputIntNumber()
        print("Nhap k: ")
        val k = inputIntNumber()
      doListTheSubsets(n, k)
    }

    private fun doListTheSubsets(n: Int, k: Int) {
        val a = IntArray(k)
        for (i in 0 until k) {
            a[i] = i + 1
            print(a[i])
        }
        print("\n")
        var i = k - 1
        while (i >= 0) {
            if (a[i] < n - k + i + 1) {
                a[i]++

                var j: Int
                j = i + 1
                while (j < k) {
                    a[j] = a[j - 1] + 1
                    j++
                }

                j = 0
                while (j < k) {
                    print("" + a[j])
                    j++
                }
                print("\n")

                i = k
            }
            i--
        }
    }
}
