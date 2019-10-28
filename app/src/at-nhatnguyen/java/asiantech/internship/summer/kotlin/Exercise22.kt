package asiantech.internship.summer.kotlin

private fun divisor(n: Int) {
    var count = 0
    print("\nCac uoc cua $n la:")
    for (i in 1..n) {
        if (n % i == 0) {
            print(" $i")
            count++
        }
    }
    println("\nCo $count uoc")
}

private fun primeDivisor(n: Int) {
    var count = 0
    print("\nCac uoc cua $n la:")
    for (i in 1..n) {
        if (n % i == 0 && isPrimeNumber(i)) {
            print(" $i")
            count++
        }
    }
    println("\nCo $count uoc la so nguyen to")
}

fun main() {
    print("Nhap n")
    val n = input()
    divisor(n)
    primeDivisor(n)
}
