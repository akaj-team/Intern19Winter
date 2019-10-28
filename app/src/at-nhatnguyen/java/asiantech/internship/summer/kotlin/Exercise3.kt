package asiantech.internship.summer.kotlin

import java.util.Scanner

fun main() {
    var surplus: Int
    var sum = 0
    val scan = Scanner(System.`in`)
    print("Nhap day so nguyen: ")
    var a = scan.nextInt()
    while (a > 0) {
        surplus = a % 10
        a /= 10
        sum += surplus
    }
    println("Tong cac so la:$sum")
}
