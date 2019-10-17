package asiantech.internship.summer.kotlin

private fun result(a: IntArray, k: Int) {
    var i = 1
    println()
    while (i <= k) {
        print(" " + a[i])
        i++
    }
}

private fun tryBackTrack(a: IntArray, n: Int, k: Int, i: Int) {
    var j: Int = a[i - 1] + 1
    while (j <= n - k + i) {
        a[i] = j
        if (i == k)
            result(a, k)
        else
            tryBackTrack(a, n, k, i + 1)
        j++
    }
}

fun main() {
    val shareds = Shared()
    print("Nhap n")
    val n = shareds.input()
    val array = IntArray(n + 1)
    var k = 1
    print("Liet ke tat ca cac tap con k phan tu cua 1,2,..,$n : ")

    while (k <= n) {
        print("\n Tap con $k phan tu: ")
        tryBackTrack(array, n, k, 1)
        k++
    }
}
