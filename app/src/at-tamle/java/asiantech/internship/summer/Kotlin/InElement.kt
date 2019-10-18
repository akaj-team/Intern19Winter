package asiantech.internship.summer.Kotlin
import java.util.Scanner
object InElement {
    fun nhap(): Int {
        val input = Scanner(System.`in`)
        var n = 0
        n = input.nextInt()
        return n
    }
    fun countElement(a: IntArray, n: Int, i: Int): Int {
        var count = 0
        for (j in 0 until n) {
            if (a[j] == i)
                count++
        }
        return count
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val n: Int
        var i: Int
        println("Nhap n= ")
        n = nhap()
        val array = IntArray(n)
        i = 0
        while (i < n) {
            println("Nhap phan tu thu " + (i + 1) + " ")
            array[i] = nhap()
            i++
        }
        i = 0
        while (i < n) {
            if (countElement(array, i, array[i]) == 0) {
                println(
                    "Phan tu " + array[i] + " xuat hien " + countElement(
                        array,
                        n,
                        array[i]
                    ) + " lan"
                )
            }
            i++
        }
    }

}
