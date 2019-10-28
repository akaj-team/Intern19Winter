package asiantech.internship.summer.kotlin

private fun totalDigits(n: Int): Int {
    var a = n
    var b = 0
    while (a > 0) {
        b += a % 10
        a /= 10
    }
    return b
}

private fun analysis(n: Int) {
    var a: Int = n
    var i = 2
    while (a > 1) {
        if (isPrimeNumber(i)) {
            if (a % i == 0) {
                print("$i.")
                a /= i
            } else {
                i++
            }
        } else {
            i++
        }
    }
}

fun main() {
    print("Nhap n")
    val n = input()
    print("n= 1")
    analysis(n)
    println("Tong cac chu so cua " + n + " la: " + totalDigits(n))
}
