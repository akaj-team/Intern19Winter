package asiantech.internship.summer.kotlin

fun main() {
    val shareds = Shared()
    print("Nhap n= ")
    val n = shareds.input()
    val f = IntArray(n)
    f[0] = 1
    f[1] = 1
    var i = 1
    var count = 1
    print("Cac so Fibonanci nho hon $n la so nguyen to: \n 1")
    while (f[i] < n) {
        if (shareds.isPrimeNumber(f[i])) {
            print(" " + f[i])
            count++
        }
        i++
        f[i] = f[i - 1] + f[i - 2]
    }
    println("\n Co $count so thoa man")
}
