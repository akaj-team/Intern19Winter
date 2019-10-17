package asiantech.internship.summer.kotlin

private fun analysis(n: Int) {
    var n: Int = n
    var i = 2
    while (n > 1) {
        if (n % i == 0) {
            print(i)
            n /= i
            if (n > 1){
                print("x")
            }
        } else
            i++
    }
}

fun main() {
    val shareds = Shared()
    print("Nhap n :")
    val n = shareds.input()
    print("$n = ")
    analysis(n)
}

