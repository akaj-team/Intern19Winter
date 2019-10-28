package asiantech.internship.summer.kotlin.advance

import android.os.Build
import com.google.gson.GsonBuilder
import java.io.FileReader
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val gson = GsonBuilder().create()
        val file = "D:\\ASIAN TECH\\EXERCISE\\Intern19Winter\\app\\src\\at-nhatnguyen\\java\\asiantech\\internship\\summer\\kotlin\\advance\\data.json"
        val students = gson.fromJson(FileReader(file), Array<Student>::class.java).toList()
        // students.forEach{println(it)}
        val majors = students.groupBy { it.major }
        val ranks = students.groupBy { it.graduate_rank }
        //val lops = students.groupBy { it.class_name }
        //println(majors)
        showMenu()
        var check = true
        while (check) {
            print("nhap số theo menu : ")
            val scanner = Scanner(System.`in`)
            when (scanner.nextLine()) {
                "1" -> println("${majors.keys}")
                "2" -> majors.forEach {
                    println(it.key + " có " + it.value.count() + " sinh viên")

                }
                "3" -> majors.forEach {
                    println(it.key + " có " + it.value.groupBy { it.class_name }.count() + " lớp")
                }
                "4" -> {
                    println("Nhập tên chuyên ngành : ")
                    val scan = Scanner(System.`in`)
                    val a = scan.nextLine()
                    println("Tất cả sinh viên thuộc chuyên ngành $a: ")
                    students.filter { it.major == a }.forEach {
                        println(it)
                    }
                }
                "5" -> ranks.forEach {
                    println("Học lực " + it.key + " có " + it.value.count() + " sinh viên")
                }
                "6" -> {
                    print("Nhập n học sinh đầu tiên : ")
                    val n = Integer.parseInt(scanner.nextLine())
                    println("Sort by: 1. name 2. birthday 3. student_id 4. degreeNo 5. id")
                    when (scanner.nextLine()) {
                        "1" -> {
                            students.sortedBy { it.name }
                                    .forEachIndexed { index, student ->
                                        if (index < n) println(student)
                                    }
                        }
                        "2" -> {
                            students.sortedBy { SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(it.birthday) }
                                    .forEachIndexed { index, student ->
                                        if (index < n) println(student)
                                    }
                        }
                        "3" -> {
                            students.sortedBy { it.student_id }
                                    .forEachIndexed { index, student ->
                                        if (index < n) println(student)
                                    }
                        }
                        "4" -> {
                            students.sortedBy { it.degree_no }
                                    .forEachIndexed { index, student ->
                                        if (index < n) println(student)
                                    }
                        }
                        "5" -> {
                            students.sortedBy { it.id }
                                    .forEachIndexed { index, student ->
                                        if (index < n) println(student)
                                    }
                        }
                    }
                }
                "7" -> {
                    println("Nhập tên sinh viên muốn tìm : ")
                    val scan = Scanner(System.`in`)
                    val b = scan.nextLine()
                    println("All the student in $b: ")
                    students.filter { it.name == b }.forEach {
                        println(it)
                    }
                }
                "8" -> {
                    println("Nhập lớp bạn muốn tìm : ")
                    val scan = Scanner(System.`in`)
                    val c = scan.nextLine()
                    println("All the student in $c: ")
                    students.filter { it.class_name == c }.forEach {
                        println(it)
                    }
                }
                "9" -> {
                    println("Nhập chuyên ngành bạn muốn tìm : ")
                    val scan = Scanner(System.`in`)
                    val d = scan.nextLine()
                    println("All the student in $d: ")
                    students.filter { it.major == d }.forEach {
                        println(it)
                    }
                }
                "10" -> {
                    print("Enter month: ")
                    val m = scanner.nextLine()
                    students.filter {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            LocalDate.parse(it.birthday, DateTimeFormatter.ofPattern("dd/MM/yyyy")).month.value == m.toInt()
                        } else {
                            val date = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(it.birthday)
                            date?.month?.plus(1) == m.toInt()
                        }
                    }.forEach { println(it) }
                }
                "exit" -> {
                    check = false
                }

            }

        }
    }

    private fun showMenu() {
        println(
                " 1 : Hiển thị tất cả các chuyên ngành \n" +
                        " 2 : Hiển thị số sinh viên trong các chuyên ngành \n" +
                        " 3 : Hiển thị tổng số lớp của các chuyên ngành \n" +
                        " 4 : Nhập chuyên ngành và hiển thị các sinh viên trong chuyên ngành \n" +
                        " 5 : Hiển thị số lượng sinh viên theo học lực \n" +
                        " 6 : Sắp xếp và hiển thị n sinh viên đầu tiên \n" +
                        " 7 : Tìm thông tin sinh viên theo tên \n" +
                        " 8 : Tìm thông tin sinh viên theo lớp \n" +
                        " 9 : Tìm thông tin sinh viên theo chuyên ngành \n" +
                        "10 : Tìm thông tin sinh viên theo tháng sinh nhật \n" +
                        "exit : Thoát"

        )
    }
}
