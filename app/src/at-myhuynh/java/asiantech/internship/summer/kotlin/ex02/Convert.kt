package asiantech.internship.summer.kotlin.ex02

import asiantech.internship.summer.kotlin.common.Common

object Convert {
    @JvmStatic
    fun main(args: Array<String>) {
        val a = Common.input("Nhập vào một số tự nhiên:")
        val b = Common.input("Nhập vào cơ số cần chuyển sang(1 < b < 36):")
        convertBase(a, b)
    }

    private fun convertBase(n: Int, base: Int) {
        if (n >= base) {
            convertBase(n / base, base)
        }
        if (n % base > 9) {
            print(n % base + 55)
        } else {
            print(n % base)
        }
    }

}
