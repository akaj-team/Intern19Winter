package asiantech.internship.summer.kotlin

import java.util.*

fun main() {

    val scan = Scanner(System.`in`)
    print("Nhập n = ")
    val n = scan.nextInt()
    if (n == 1) {
        println("Không có số nguyên tố nhỏ hơn 1 ")
    } else {
        System.out.printf("Tất cả các số nguyên tố nhỏ hơn %d là: \n", n)

        if (n >= 2) {
            print(2.toString())
        }
        var i = 3
        while (i < n) {
            if (isPrimeNumber(i)) {
                print(" $i")
            }
            i += 2
        }
    }
}

