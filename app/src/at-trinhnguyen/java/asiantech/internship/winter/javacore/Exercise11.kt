package asiantech.internship.winter.javacore

/*
Bài 11. Viết chương trình liệt kê tất cả các hoán vị của 1, 2, .., n.
 */

import java.util.*

object Exercise11 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter n: ")
        val n = scanner.nextInt()
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
        println("All permutation: ")
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
                            //Tim so a[k] nho nhat ma >a[i] trong cac so ben phai a[i]
                            k = i + 1
                            j = i + 1
                            while (j < n) {
                                if (array[i + 1] > array[j] && array[j] > array[i]) k = j
                                j++
                            }
                            //Doi cho a[k] va a[i]
                            temp = array[i]
                            array[i] = array[k]
                            array[k] = temp
                            //Sap xep lai tu a[i+1] toi a[n]
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
                        // break;
                    }
                    i--
                }
                //if(i==0)check=0;
            }
        } catch (e: Exception) {
        }

    }
}
