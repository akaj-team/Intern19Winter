package asiantech.internship.summer.kotlin

private fun isReversibleNumber(n: Int): Boolean {
    val x = StringBuilder()
    val str = "" + n
    x.append(str)
    val check = "" + x.reverse()
    return str == check
}

fun main() {
    var n = 100000
    var count = 0
    while (n <= 999999) {
        if (isReversibleNumber(n)) {
            println(n)
            count++
        }
        n++
    }
    print("Co $count so thuan nghich co 6 chu so")
}


