package asiantech.internship.summer.kotlincore.exercise16

import inputIntNumber
import java.util.*

object TheNumberOfOccurrencesOfTheElement {

    @JvmStatic
    fun main(args: Array<String>) {
        print("Nhập số phần tử của mảng: ")
        val n = inputIntNumber()
        val arr = IntArray(n)
        print("Nhập các phần tử của mảng: \n")
        for (i in 0 until n) {
            System.out.printf("a[%d] = ", i)
            arr[i] = inputIntNumber()
        }
        val map = TreeMap<Int, Int>()
        for (i in 0 until n) {
          addElement(
              map, arr[i])
        }
        for (key in map.keys) {
            System.out.printf("%d xuất hiện %d lần.\n", key, map[key])
        }
    }

    private fun addElement(map: MutableMap<Int, Int>, element: Int) {
        if (map.containsKey(element)) {
            var count = map[element]!! + 1
            map[element] = count
        } else {
            map[element] = 1
        }
    }
}
