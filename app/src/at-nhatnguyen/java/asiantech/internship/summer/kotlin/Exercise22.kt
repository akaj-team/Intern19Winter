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
    val shareds = Shared()
    var count = 0
    print("\nCac uoc cua $n la:")
    for (i in 1..n) {
        if (n % i == 0 && shareds.isPrimeNumber(i)) {
            print(" $i")
            count++

        }

    }
    println("\nCo $count uoc la so nguyen to")

}
fun main() {
    val shareds = Shared()
    print("Nhap n")
    val n = shareds.input()
    divisor(n)
    primeDivisor(n)

}



