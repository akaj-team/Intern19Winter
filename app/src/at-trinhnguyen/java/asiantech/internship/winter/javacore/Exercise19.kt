package asiantech.internship.winter.javacore

import java.util.*

/*
Bài 19. Viết chương trình liệt kê tất cả các số nguyên tố có 5 chữ số
sao cho tổng của các chữ số trong mỗi số nguyên tố đều bằng S cho trước.
 */
object Exercise19 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter sum : ")
        val sum = scanner.nextInt()
        for (i in 10000..99999) {
            if (i.isPrimeNumber()) {
                if (i.sum() == sum) {
                    println(i)
                }
            }
        }
    }
}
