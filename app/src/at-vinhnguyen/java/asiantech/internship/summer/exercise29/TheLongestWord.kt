package exercise29

import java.util.*

object TheLongestWord {

    @JvmStatic
    fun main(args: Array<String>) {
        print("Nhap tu ban can tim:")
        val str = Scanner(System.`in`).nextLine()
        findTheLongestWord(str)
    }

    private fun findTheLongestWord(str: String) {
        var position = 0
        var positionTemp = 0
        var length = 0
        while (position <= str.length) {
            var lengthTemp = 0
            var i = position
            while (i != str.length && str[i] != ' ') {
                lengthTemp++
                i++
            }
            if (lengthTemp > length) {
                length = lengthTemp
                positionTemp = position
            }
            position += lengthTemp + 1
        }
        println("Ky tu co do dai lon nhat la: " + str.substring(positionTemp, positionTemp + length))
    }
}
