package asiantech.internship.summer.Kotlin
import java.util.Scanner
object Subset {
    fun process(): Int {
        val input = Scanner(System.`in`)

        var n = 0

                n = input.nextInt()

        return n
    }

    private fun result(a: IntArray, k: Int) {
        var i: Int
        println()
        i = 1
        while (i <= k) {
            print(" " + a[i])
            i++
        }
    }

    private fun try_backTrack(a: IntArray, n: Int, k: Int, i: Int) {
        var j: Int
        j = a[i - 1] + 1
        while (j <= n - k + i) {
            a[i] = j
            if (i == k)
                result(a, k)
            else
                try_backTrack(a, n, k, i + 1)
            j++
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap n")
        val n = process()
        val array = IntArray(n + 1)
        var k: Int
        println("Liet ke tat ca cac tap con k phan tu cua 1,2,..,$n : ")
        k = 1
        while (k <= n) {
            println("\n Tap con $k phan tu: ")
            try_backTrack(array, n, k, 1)
            k++
        }
    }
}
