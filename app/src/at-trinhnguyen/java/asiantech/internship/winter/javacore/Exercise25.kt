package asiantech.internship.winter.javacore

/*
Bài 25. Viết chương trình liệt kê các số nguyên có 7 chữ số thoả mãn:
Là số nguyên tố.
Là số thuận nghịch.
Tổng các chữ số của số đó là một số thuận nghịch
 */
object Exercise25 {
    @JvmStatic
    fun main(args: Array<String>) {
        for (i in 1000000..9999999) {
            if (i.isPrimeNumber()) {
                print("$i ")
            }
        }
        for (i in 1000000..9999999) {
            if (i.toString().isReversible()) {
                print("$i ")
            }
        }
        for (i in 1000000..9999999) {
            if (i.sum().toString().isReversible()) {
                print("$i ")
            }
        }

    }
}
