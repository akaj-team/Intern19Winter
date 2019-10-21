package asiantech.internship.summer.kotlincore.exercise25

import isPrime
import isReversible
import sumOfNumber

object Exercise25 {

    @JvmStatic
    fun main(args: Array<String>) {
        var count = 0
        println("cac so co 7 chu so thoa man dieu kien la: ")
        var i = 1000001
        while (i < 9999999) {
            if (i.isPrime() && i.sumOfNumber().isReversible() && i.isReversible()) {
                println(" $i")
                count++
            }
            i += 2
        }
        println("\n Co $count so thoa man")
    }
}
