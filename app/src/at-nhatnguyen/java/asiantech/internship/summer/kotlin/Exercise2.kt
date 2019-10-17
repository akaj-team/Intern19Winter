package asiantech.internship.summer.kotlin

import java.util.Scanner


private fun base(n: Int, base: Int) {
    if (n >= base) base(n / base, base)
    if (n % base > 9)
        System.out.printf("%c", n % base + 55)
    else
        print(n % base)
}
fun main() {
    val input:Shared  =  Shared()
    print("Nhap n :")
    val n = input.input()
    print("Nhap vao co so can chuyen sang b :")
    val b = input.input()
    print("So $n chuyen sang co so $b thanh: ")
    base(n, b)
}

