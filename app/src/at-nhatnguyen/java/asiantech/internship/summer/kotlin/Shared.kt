package asiantech.internship.summer.kotlin

import java.util.*

class Shared {


     fun input(): Int {
        val scan = Scanner(System.`in`)
        var check = false
        var n = 0
        while (!check) {
            print(" ")
            try {
                n = scan.nextInt()
                check = true

            } catch (e: Exception) {
                println("Bạn phải nhập số :")
                scan.nextLine()
            }

        }
        return n
    }

     fun isPrimeNumber(n: Int): Boolean {
        if (n < 2) {
            return false
        }
        for (i in 2 until n) {
            if (n % i == 0) {
                return false
            }
        }
        return true
    }
}