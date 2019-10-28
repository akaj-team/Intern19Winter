package asiantech.internship.summer.kotlin

import java.util.Scanner

object Convert {

    private fun radix(n: Int, k: Int) {
        if (n >= k) radix(n / k, k)
        if (n % k > 9) {
            println("%c" + n % k + 55)
        } else {
            print(n % k)
        }
    }

    private fun process(): Int {
        val input = Scanner(System.`in`)
        var check = false
        var n = 0
        while (!check) {
            println(" ")
            try {
                n = input.nextInt()
                check = true

            } catch (e: Exception) {
                println("Ban phai nhap so! hay nhap lai...")
                input.nextLine()
            }

        }
        return n
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap n")
        val n = process()
        println("Nhap vao co so can chuyen sang b")
        val b = process()
        println("So $n chuyen sang co so $b thanh: ")
        radix(n, b)
    }
}

