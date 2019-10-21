package asiantech.internship.summer.kotlincore.exercise26

import addElement
import inputIntNumber
import maxPositionAfter
import printArray
import java.util.*

object Exercise26 {
  @JvmStatic
  fun main(args: Array<String>) {
    print("Nhap n= ")
    val n = inputIntNumber()
    val a = IntArray(n + 1)
    var i = 0
    while (i < n) {
      print("\n Nhap phan tu thu $i = ")
      a[i] = inputIntNumber()
      i++
    }
    i = 0
    while (i < n) {
      if (a[i] == a[a.maxPositionAfter(n)]) {
        println(" Phan tu thu " + i + " lon thu 2 trong mang a[" + i + "]= " + a[i])
      }
      i++
    }
    Arrays.sort(a)
    a.printArray(1, n + 1)
    print("Nhap phan tu muon them = ")
    val element = inputIntNumber()
    a.addElement(element)
    a.printArray(0, n)
  }
}
