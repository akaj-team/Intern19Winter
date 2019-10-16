package exercise18

import java.util.*

object CountTheNumberOfWords {

    val SPACE = ' '
    val TAB = '\t'
    val BREAK_LINE = '\n'

    @JvmStatic
    fun main(args: Array<String>) {
        print("Nhap chuoi can tim: ")
        val str = Scanner(System.`in`).nextLine()
        print(
            "Số từ của chuỗi đã cho là: "
                    + countWords(str) + "\n"
        )
    }

    fun countWords(input: String?): Int {
        if (input == null) {
            return -1
        }
        var count = 0
        val size = input.length
        var notCounted = true
        for (i in 0 until size) {
            if (input[i] != SPACE && input[i] != TAB
                && input[i] != BREAK_LINE
            ) {
                if (notCounted) {
                    count++
                    notCounted = false
                }
            } else {
                notCounted = true
            }
        }
        return count
    }
}
