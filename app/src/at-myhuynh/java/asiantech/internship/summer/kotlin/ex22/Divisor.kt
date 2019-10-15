package asiantech.internship.summer.kotlin.ex22

import asiantech.internship.summer.kotlin.common.Common
import asiantech.internship.summer.kotlin.common.isPrime
import java.util.ArrayList

object Divisor {

    @JvmStatic
    fun main(args: Array<String>) {
        val n = Common.input("Nhập vào số n:")
        val listDiv = getListDiv(n)

        println("Danh sach uoc so: $listDiv")
        println("So luong uoc so: " + listDiv.size)
        println("Danh sach uoc so la so nguyen to: " + getListDivPrime(listDiv).toString())
    }

    private fun getListDivPrime(listDiv: List<Int>): List<Int> {
        val lisDivPrime = ArrayList<Int>()
        for (i in listDiv.indices) {
            if (listDiv[i].isPrime()) {
                lisDivPrime.add(listDiv[i])
            }
        }
        return lisDivPrime
    }

    private fun getListDiv(n: Int): List<Int> {
        val listDiv = ArrayList<Int>()
        for (i in 1..n) {
            if (n % i == 0) {
                listDiv.add(i)
            }
        }
        return listDiv
    }
}
