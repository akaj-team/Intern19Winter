package asiantech.internship.summer.Kotlin
import java.util.Scanner
import java.util.StringTokenizer
object Surname {
    private fun total(s: String) {
        val t = StringTokenizer(s)
        val k = StringBuffer()
        val s1 = arrayOfNulls<String>(10)
        var i = 1
        while (t.hasMoreTokens()) {
            s1[i] = t.nextToken()
            i++
        }
        k.append(s1[3]).append("  ")
        k.append(s1[1]).append("  ")
        k.append(s1[2]).append("  ")
        println(k)
    }
    @JvmStatic
    fun main(args: Array<String>) {
        var s0 = String()
        val `in` = Scanner(System.`in`)
        println("nhap xau ho ten: ")
        s0 = `in`.nextLine()
        total(s0)
    }
}
