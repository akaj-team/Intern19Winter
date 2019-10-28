package asiantech.internship.summer.kotlin.ex08

import asiantech.internship.summer.kotlin.common.Common
import asiantech.internship.summer.kotlin.common.isChainOfSymmetry

object ChainOfSymmetry {

    @JvmStatic
    fun main(args: Array<String>) {
        val str = Common.inputString("Nhập vào chuỗi: ")
        if (str.isChainOfSymmetry()) {
            println("$str Thuan nghich")
        } else {
            println("$str Khong thuan nghich")
        }
    }
}
