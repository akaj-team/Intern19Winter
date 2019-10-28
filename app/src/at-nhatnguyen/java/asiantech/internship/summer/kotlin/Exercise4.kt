package asiantech.internship.summer.kotlin

private fun analysis(n: Int) {
    var a: Int = n
    var i = 2
    while (a > 1) {
        if (a % i == 0) {
            print(i)
            a /= i
            if (a > 1) {
                print("x")
            }
        } else {
            i++
        }
    }
}

fun main() {
    print("Nhap n :")
    val n = input()
    print("$n = ")
    analysis(n)
}
