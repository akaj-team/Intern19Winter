package asiantech.internship.summer.kotlin

private fun primeNumber(n: Int) {
    var i = 1
    var count = 0
    println("Cac so nguyen to nho hon $n la: ")
    while (i < n) {
        if (isPrimeNumber(i)) {
            print(" $i")
            count++
        }
        i++
    }
    println("\n Co $count so thoa man")
}

fun main() {
    print("Nhap n")
    val n = input()
    primeNumber(n)
    val f = IntArray(n)
    f[0] = 1
    f[1] = 1
    var i = 1
    print("Cac so Fibonanci nho hon $n la : \n 1")
    while (f[i] < n) {
        print(" " + f[i])
        i++
        f[i] = f[i - 1] + f[i - 2]
    }
    println("\n Co $i so thoa man")
}
