package asiantech.internship.winter.javacore

import java.util.*

/*
Bài 8. Một số được gọi là số thuận nghịch độc nếu ta đọc
 từ trái sang phải hay từ phải sang trái số đó ta vẫn nhận được
  một số giống nhau. Hãy liệt kê tất cả các số thuận nghịch độc
  có sáu chữ số (Ví dụ số: 558855).
 */
object Bai8 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter n: ")
        val n = scanner.nextInt()
        if (isReversible(n.toString())) {
            print("$n is reversible")
        } else print("$n is not reversible")

    }

    private fun isReversible(n: String): Boolean {
        return n.equals(StringBuffer(n).reverse().toString())
    }
}