package asiantech.internship.summer.Kotlin
import java.util.Scanner
object Permutation {
    fun process(): Int {
        val input = Scanner(System.`in`)

        var n = 0
                n = input.nextInt()

        return n
    }
    @JvmStatic
    fun main(args: Array<String>) {
        println("Nhap n")
        val n = process()
        val array = IntArray(n + 2)
        var i: Int
        var j: Int
        var k = n - 1
        var temp: Int
        var check = 1
        i = 0
        while (i < n) {
            array[i] = i + 1
            i++
        }
        println("Cac hoan vi ke la: ")
        try {
            i = n - 2
            while (check > 0) {

                j = 0
                while (j < n) {
                    print(" " + array[j])
                    j++
                }
                i = n - 2
                while (i >= 0) {
                    check = 1
                    if (array[i] < array[i + 1]) {
                        if (i == n - 2) {
                            temp = array[i]
                            array[i] = array[n - 1]
                            array[n - 1] = temp
                            break
                        } else {

                            k = i + 1
                            j = i + 1
                            while (j < n) {
                                if (array[i + 1] > array[j] && array[j] > array[i]) k = j
                                j++
                            }

                            temp = array[i]
                            array[i] = array[k]
                            array[k] = temp

                            j = i + 1
                            while (j < n) {
                                for (m in i + 1 until n) {
                                    if (array[j] < array[m]) {
                                        temp = array[j]
                                        array[j] = array[m]
                                        array[m] = temp
                                    }
                                }
                                j++
                            }
                            break
                        }
                    } else {
                        check = 0

                    }
                    i--
                }

            }
        } catch (e: Exception) {
        }

    }
}
