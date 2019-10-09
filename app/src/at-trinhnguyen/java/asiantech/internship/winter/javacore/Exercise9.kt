package asiantech.internship.winter.javacore

import java.util.*


/*
Bài 9. Viết chương trình liệt kê tất cả các xâu nhị phân độ dài n.
 */
object Exercise9 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter n: ")
        val n = scanner.nextInt()

        val array = IntArray(n)
        var tich: Int
        do {
            tich = 1

            println("")
            for (j in 0 until n) {
                print(" " + array[j])
                tich *= array[j]
            }
            var i = n - 1
            do {
                if (array[i] == 0) {
                    array[i] = 1
                    for (j in n - 1 downTo i + 1) {
                        array[j] = 0
                    }
                    break
                } else
                    i--
            } while (i >= 0)
        } while (tich != 1)

    }
}


