package asiantech.internship.summer.kotlin.ex30

import asiantech.internship.summer.kotlin.common.Common

object StringStandardize {

    @JvmStatic
    fun main(args: Array<String>) {
        var name = Common.inputString("Nhập vào họ tên:")
        name = name.replace("\\s+".toRegex(), " ").trim()

        val firstName = name.substring(0, name.indexOf(' '))
        val middleName = name.substring(name.indexOf(' ') + 1, name.lastIndexOf(' '))
        val lastName = name.substring(name.lastIndexOf(' ') + 1)

        val nameStand = "$lastName $middleName $firstName"
        println("$name => $nameStand")
    }

}
