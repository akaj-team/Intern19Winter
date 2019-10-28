package asiantech.internship.summer.kotlin

import java.util.Scanner

object Binary {
    fun process(): Int {
        val input = Scanner(System.`in`)
        var check = false
        var n = 0
        while (!check) {
            println("Nhap n ")
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
        val n = process()
        val array = IntArray(n)
        var k: Int
        do {
            k = 1
            for (j in 0 until n) {
                print(" " + array[j])
                k *= array[j]
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
        } while (k != 1)
    }
}