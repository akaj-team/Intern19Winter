package asiantech.internship.summer.kotlincore.exercise23

import asiantech.internship.summer.kotlincore.inputIntNumber
import asiantech.internship.summer.kotlincore.isPrime

object Exercise23 {

    private fun listPrimeNumbers(n: Int) {
        var i = 1
        var count = 0
        println("Cac so nguyen to nho hon $n la: ")
        while (i < n) {
            if (i.isPrime()) {
                print(" $i")
                count++
            }
            i++
        }
        println("\n Co $count so thoa man")
    }

    @JvmStatic
    fun main(args: Array<String>) {
        print("Nhap n")
        val n = inputIntNumber()
        listPrimeNumbers(n)
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
}
