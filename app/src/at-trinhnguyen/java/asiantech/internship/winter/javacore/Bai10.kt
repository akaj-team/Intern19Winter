package asiantech.internship.winter.javacore

import java.util.*


/*
Bài 10. Viết chương trình liệt kê tất cả các tập con k phần tử của 1, 2, ..,n (k≤n).
 */
object Bai10 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter n: ")
        val n = scanner.nextInt()

        val array = IntArray(n + 1)

        var k = 1
        while (k <= n) {
            println("\n Subsets $k-element: ")
            tryBacktrack(array, n, k, 1)
            k++
        }
    }

    private fun result(a: IntArray, k: Int) {
        var i = 1
        println()
        while (i <= k) {
            print("  " + a[i])
            i++
        }
    }

    private fun tryBacktrack(a: IntArray, n: Int, k: Int, i: Int) {
        var j: Int = a[i - 1] + 1
        while (j <= n - k + i) {
            a[i] = j
            if (i == k)
                result(a, k)
            else
                tryBacktrack(a, n, k, i + 1)
            j++
        }
    }
}