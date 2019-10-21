package asiantech.internship.summer.kotlin.ex22

import asiantech.internship.summer.kotlin.common.Common
import asiantech.internship.summer.kotlin.common.getListDiv
import asiantech.internship.summer.kotlin.common.isPrime

object Divisor {

    @JvmStatic
    fun main(args: Array<String>) {
        val n = Common.input("Nhập vào số n:")
        val listDiv = n.getListDiv()

        println("Danh sach uoc so: $listDiv")
        println("So luong uoc so: " + listDiv.size)
        println("Danh sach uoc so la so nguyen to: " + getListDivPrime(listDiv).toString())
    }

    private fun getListDivPrime(listDiv: List<Int>): List<Int> {
        return listDiv.filter(Int::isPrime)
    }
}
