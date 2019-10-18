package asiantech.internship.summer.Kotlin

import java.util.Scanner
object Binary {
    fun process(): Int {
        val input = Scanner(System.`in`)
        var n = 0

                n = input.nextInt()

        return n
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val n = process()
        val array = IntArray(n)
        var New: Int
        do {
            New = 1
            println()

            for (j in 0 until n) {
                print(" " + array[j])
                New *= array[j]
            }
            var i = n - 1
            do {
                if (array[i] == 0) {
                    array[i] = 1
                    for (j in n - 1 downTo i + 1) {
                        array[j] = 0
                    }
                    break
                } else
                    i--
            } while (i >= 0)
        } while (New != 1)
    }
}
