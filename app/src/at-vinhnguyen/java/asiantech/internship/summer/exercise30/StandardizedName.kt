package exercise30

import java.util.*

object StandardizedName {

    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap ten cua ban: ")
        val fullName = Scanner(System.`in`).nextLine()
        val lastNamePosition = fullName.lastIndexOf(" ") + 1
        println(
            "Your name: " + fullName.substring(lastNamePosition) + " " + fullName.substring(
                0, lastNamePosition - 1
            )
        )
    }
}
