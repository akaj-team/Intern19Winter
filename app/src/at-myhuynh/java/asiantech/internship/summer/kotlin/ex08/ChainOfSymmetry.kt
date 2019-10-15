package asiantech.internship.summer.kotlin.ex08

import asiantech.internship.summer.kotlin.common.Common

object ChainOfSymmetry {

    @JvmStatic
    fun main(args: Array<String>) {
        val str = Common.inputString("Nhập vào chuỗi: ")
        val check = str == StringBuffer(str).reverse().toString()
        if (check) {
            println("$str Thuan nghich")
        } else {
            println("$str Khong thuan nghich")
        }
    }
}
