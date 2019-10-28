package asiantech.internship.summer.kotlin

private fun countElement(a: IntArray, n: Int, i: Int): Int {
    var count = 0
    for (j in 0 until n) {
        if (a[j] == i) {
            count++
        }
    }
    return count
}

fun main() {
    val n: Int = input()
    var i = 0
    print("Nhap n= ")
    val array = IntArray(n)
    while (i < n) {
        print("Nhap phan tu thu " + (i + 1) + " ")
        array[i] = input()
        i++
    }
    i = 0
    while (i < n) {
        if (countElement(array, i, array[i]) == 0) {
            println(
                    "Phan tu " + array[i] + " xuat hien " + countElement(
                            array, n,
                            array[i]
                    ) + " lan"
            )
        }
        i++
    }
}

