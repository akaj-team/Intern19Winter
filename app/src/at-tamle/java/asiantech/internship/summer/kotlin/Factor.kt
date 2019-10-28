package asiantech.internship.summer.kotlin

import java.util.Scanner

object Factor {
    private fun process(n: Int) {
        var n=n
        var i = 2
        while (n > 1) {
            if (n % i == 0) {
                print(i)
                n /= i
                if (n > 1) print(".")
            } else
                ( i++)
        }
    }

    private fun input(): Int {
        val input = Scanner(System.`in`)
        var check = false
        var n = 0
        while (!check) {
            print(" ")
            try {
                n = input.nextInt()
                check = true
            } catch (e: Exception) {
                println("nhap so hay nhap lai")
                input.nextLine()
            }

        }
        return n
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap n")
        val n = input()
        print("$n = ")
        process(n)
    }
}
