package asiantech.internship.summer.kotlin

import java.util.Scanner

fun main() {
    val scan = Scanner(System.`in`)
    print("Nhap n = ")
    val n = scan.nextInt()
    var dem = 0
    var i = 2
    System.out.printf("%d so nguyen to da tien la: \n", n)
    while (dem < n) {
        if (isPrimeNumber(i)) {
            print("$i ")
            dem++
        }
        i++
    }
}


