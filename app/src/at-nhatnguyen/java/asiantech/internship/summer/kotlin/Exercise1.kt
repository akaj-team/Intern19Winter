package asiantech.internship.summer.kotlin
import java.util.*

fun main() {
    val scan = Scanner(System.`in`)
    print("Nhap so a :")
    val a = scan.nextInt()
    print("nhap so b :")
    val b = scan.nextInt()
    print("UCLN la :" + findGreatestCommonDivisor(a, b) + "\n")
    print("BCNN la :" + findLeastCommonMultiple(a, b))
}
private fun findGreatestCommonDivisor(a: Int, b: Int): Int {
    return if (b == 0) {
        a
    } else {
        findGreatestCommonDivisor(b, a % b)

    }
}
private fun findLeastCommonMultiple(a: Int, b: Int): Int {
    return a * b / findGreatestCommonDivisor(a, b)
}
