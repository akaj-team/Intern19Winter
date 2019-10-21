package asiantech.internship.summer.kotlincore.exercise15

import inputIntNumber

object ListElementsThatAppearExactlyTwice {

  fun countElement(a: IntArray, n: Int, i: Int): Int {
    var count = 0
    for (j in 0 until n) {
      if (a[j] == i) {
        count++
      }
    }
    return count
  }

  @JvmStatic
  fun main(args: Array<String>) {
    val n: Int
    var i: Int
    print("Nhap n= ")
    n = inputIntNumber()
    val array = IntArray(n)
    i = 0
    while (i < n) {
      print("Nhap phan tu thu " + (i + 1) + " ")
      array[i] = inputIntNumber()
      i++
    }
    print("Cac phan tu trong day xuat hien 1 lan: ")
    i = 0
    while (i < n) {
      if (countElement(
              array, n, array[i]) == 2) {
        print("  " + array[i])
      }
      i++
    }
  }
}
