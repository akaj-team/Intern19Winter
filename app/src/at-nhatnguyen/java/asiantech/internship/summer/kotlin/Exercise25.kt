package asiantech.internship.summer.kotlin

private fun isReversibleNumber(n: Int): Boolean {
    val x = StringBuilder()
    val str = n.toString()
    x.append(str)
    val check = x.reverse().toString()
    return str == check
}

private fun primeNumber(n: Int): Boolean {
    var a: Int = n
    while (a != 0) {
        if (!isPrimeNumber(a % 10)) {
            return false
        }
        a /= 10
    }
    return true
}

fun main() {
    var i = 22223
    var count = 0
    println("cac so tu 5-7 chu so thoa man dieu kien la: ")
    while (i < 7777777) {
        if (isPrimeNumber(i) && primeNumber(i) && isReversibleNumber(i)) {
            println(" $i")
            count++
        }
        i += 2
    }
    println(" Co $count so thoa man")
}
