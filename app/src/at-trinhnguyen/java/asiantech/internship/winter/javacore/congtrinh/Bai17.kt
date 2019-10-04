package com.congtrinh
import com.congtrinh.a.nhap
import java.util.*


/*
Bài 17. Nhập số n và dãy các số thực a0 , a1 ,..., an-1.
Không đổi chỗ các phần tử và không dùng thêm mảng số thực nào khác
(có thể dùng mảng số nguyên nếu cần) hãy cho hiện trên màn hình dãy trên theo thứ tự tăng dần.
 */
object Bai17 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter n: ")
        val n = scanner.nextInt()
        val array = FloatArray(n)

        for (i in 0 until n) {
            println("Nhap phan tu thu ${i + 1}: ")
            array[i] = scanner.nextFloat()
        }
        println("Sap xep theo thu tu tang dan")
        for( j in 0 until n){
            println("${array[indexOfMinValue(array,n)]}")
            array[indexOfMinValue(array,n)]= maxValue(array,n)
        }
    }

    private fun indexOfMinValue(a: FloatArray, n: Int): Int {
        var min = a[0]
        var i = 0
        for (j in 0 until n) {
            if (min > a[j]) {
                min = a[j]
                i = j
            }
        }
        return i
    }

    private fun maxValue(a: FloatArray, n: Int): Float {
        var max = a[0]
        for (j in 0 until n) {
            if (max < a[j]) max = a[j]
        }
        return max
    }
}