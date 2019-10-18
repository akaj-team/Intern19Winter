package asiantech.internship.summer.KotlinW2Json
import java.util.*
object Com{
    fun input(text: String): Int {
        val sc = Scanner(System.`in`, "UTF-8")
        var n = 0
        print(text)
        n = Integer.parseInt(sc.nextLine())
        return n

    }

    fun inputString(text: String): String {
        val sc = Scanner(System.`in`, "UTF-8")
        print(text)
        return sc.nextLine()
    }


fun joinString(delimiter: String, arrStr: Array<Any>): String {
    val result = StringBuilder()
    for (i in arrStr.indices) {
        if (i == arrStr.size - 1) {
            result.append(arrStr[i])
        } else {
            result.append(arrStr[i]).append(delimiter)
        }
    }
    return result.toString()
}
}
