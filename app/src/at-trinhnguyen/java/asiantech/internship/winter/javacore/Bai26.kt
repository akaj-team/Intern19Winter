package asiantech.internship.winter.javacore

import java.util.*

/*
Bài 26. Viết chương trình nhập vào vào mảng A có n phần tử, các phần tử là những số nguyên lớn hơn 0 và nhỏ hơn 100 được nhập vào từ bàn phím. Thực hiện các chức năng sau:
Tìm phần tử lớn nhất và lớn thứ 2 trong mảng cùng chỉ số của các số đó.
Sắp xếp mảng theo thứ tự giảm dần .
Nhập một số nguyên x và chèn x vào mảng A sao cho vẫn đảm bảo tính sắp xếp giảm dần.
 */
object Bai26 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Nhap n: ")
        val n = scanner.nextInt()
        var i = 0
        val array = IntArray(n)

        while (i < n) {
            print("Nhap phan tu thu " + (i + 1) + " ")
            array[i] = scanner.nextInt()
            i++
        }
        val max1 = array.max() ?: 0
        val max1Idx = array.indexOf(max1)
        println("Max 1 = array[$max1Idx] = $max1")

        var max2 = array[0]
        var max2Idx = 0
        for (i1 in array.indices) {
            if (array[i1] != max1) {
                if (max2 < array[i1]) {
                    max2 = array[i1]
                    max2Idx = i1
                }
            }

        }
        println("Max 2 = array[$max2Idx] = $max2")


        array.sort()
        for (j in array) {
            print("Sorted: $j ")
        }
    }
}