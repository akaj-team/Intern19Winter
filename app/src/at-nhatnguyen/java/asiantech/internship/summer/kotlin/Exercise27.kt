package asiantech.internship.summer.kotlin
import java.util.Arrays

private fun max1(a: IntArray, n: Int): Int {
    var max = a[0]
    var key = 0
    for (j in 0 until n) {
        if (max < a[j]) {
            max = a[j]
            key = j
        }
    }
    return key
}

private fun inArray(a: IntArray, begin: Int, end: Int) {
    println()
    var i: Int = begin

    while (i < end) {
        print(" " + a[i])
        i++
    }
    println()
}

private fun max2(a: IntArray, n: Int): Int {
    var i = 0
    var key = 0
    var max = 0

    while (i < n) {
        if (a[i] > max && a[i] != a[max1(a, n)]) {
            max = a[i]
            key = i
        }
        i++
    }
    return key
}

private fun add(a: IntArray, pt: Int) {
    a[0] = pt
    Arrays.sort(a)
}

fun main() {
    val shareds = Shared()
    print("Nhap n= ")
    val n = shareds.input()
    val a = IntArray(n + 1)
    var i = 0
    while (i < n) {
        print("\n Nhap phan tu thu $i = ")
        a[i] = shareds.input()
        i++
    }
    i = 0
    while (i < n) {
        if (a[i] == a[max2(
                a,
                n
            )]
        ) println(" Phan tu thu " + i + " lon thu 2 trong mang a[" + i + "]= " + a[i])
        i++
    }
    Arrays.sort(a)
    inArray(a, 1, n + 1)
    print("Nhap phan tu muon them pt= ")
    val pt = shareds.input()
    add(a, pt)
    inArray(a, 0, n)
}
