package asiantech.internship.winter.javacore

/*
Bài 24. Viết chương trình liệt kê các số nguyên có từ 5 đến 7 chữ số thoả mãn:
Là số nguyên tố.
Là số thuận nghịch.
Mỗi chữ số đều là số nguyên tố
 */
object Exercise24 {
    @JvmStatic
    fun main(args: Array<String>) {
        for (i in 10000..9999999) {
            if (i.isPrimeNumber()) {
                print("$i ")
            }
        }
        println()
        for (i in 10000..9999999) {
            if (i.toString().isReversible()) {
                print("$i ")
            }
        }
        val primeChars: IntArray = intArrayOf(2, 3, 5, 7)
        for (i in 10000..9999999) {
            var n = i
            var charIsPrimeNumber = true
            while (n > 0) {
                if (!primeChars.contains(n % 10)) {
                    charIsPrimeNumber = false
                    break
                }
                n /= 10
            }
            if (charIsPrimeNumber) {
                println(i)
            }
        }
    }
}
