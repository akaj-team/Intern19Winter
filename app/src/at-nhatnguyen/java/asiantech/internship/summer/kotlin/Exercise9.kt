package asiantech.internship.summer.kotlin

fun main() {

    print("Nhap n")
    val n = input()
    val array = IntArray(n)
    var tich: Int
    do {
        tich = 1
        print(" ")
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
            } else {
                i--
            }
        } while (i >= 0)
    } while (tich != 1)
}
