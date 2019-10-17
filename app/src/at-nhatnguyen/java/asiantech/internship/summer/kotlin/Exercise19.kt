package asiantech.internship.summer.kotlin

private fun totalDigits(n: Int): Int {
    var a: Int = n
    var b = 0
    while (a > 0) {
        b += a % 10
        a /= 10
    }
    return b
}


fun main() {
    val shareds = Shared()
    print("Nhap S= ")
    val s = shareds.input()
    var i = 10000
    var count = 0
    println("Cac so nguyen to co tong cac chu so co tong bang $s la: ")
    while (i <= 99999) {
        if (shareds.isPrimeNumber(i)) {
            if (totalDigits(i) == s) {
                println(" $i")
                count++
            } else {
                i++
                continue
            }
        }
        i++
    }
    println("Co $count so thoa man")
}
