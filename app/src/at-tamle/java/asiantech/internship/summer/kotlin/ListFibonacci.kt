package asiantech.internship.summer.kotlin

import java.util.Scanner

object ListFibonacci {
    private fun process(): Int {
        val input = Scanner(System.`in`)
        var check = false
        var n = 0
        while (!check) {

            try {
                n = input.nextInt()
                check = true
            } catch (e: Exception) {
                println("hay nhap lai")
                input.nextLine()
            }

        }
        return n
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap n")
        val n = process()
        val f = IntArray(n + 1)
        f[0] = 1
        f[1] = 1
        for (i in 2..n) {
            f[i] = f[i - 1] + f[i - 2]
        }
        println("So Fibonanci thu $n la: f[$n]= " + f[n])
    }
}
