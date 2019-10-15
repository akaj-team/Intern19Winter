package asiantech.internship.summer.kotlin.advance

import asiantech.internship.summer.kotlin.common.Common
import com.google.gson.Gson
import java.io.File
import java.io.FileNotFoundException
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList

object FileControl {
    @JvmStatic
    fun main(args: Array<String>) {
        val students = "./app/src/at-myhuynh/assets/data.json".readFile()
        var check = true
        do {
            when (mainMenu()) {
                1 -> allMajor(students)
                2 -> countStudent(students)
                3 -> countClass(students)
                4 -> filterStudentByMajor(students)
                5 -> countStudentByRank(students)
                6 -> sortStudent(students)
                7 -> findStudentByName(students)
                8 -> findStudentByClass(students)
                9 -> findStudentByMajor(students)
                10 -> findStudentByMonthOfBirth(students)
                else -> check = false
            }
        } while (check)
    }

    private fun mainMenu(): Int {
        println(
                "\n1. Show all the major on that list." +
                        "\n2. Count how many students in the inputted major." +
                        "\n3. Count how many classes in the inputted major." +
                        "\n4. Input major, show all the students in that major." +
                        "\n5. Count the number of students for each degree rank." +
                        "\n6. Sort the student and show the first n students." +
                        "\n7. Find students by name." +
                        "\n8. Find students by class." +
                        "\n9. Find students by major." +
                        "\n10. Input the month, show all the students have birthday in that month."
        )
        return Common.input("Nhập vào số tương ứng: ")
    }

    private fun findStudentByMonthOfBirth(students: List<Student>) {
        val month = Common.input("Nhập vào tháng: ")
        if (month in 1..12) {
            for (student in students) {
                val dayOfBirth = student.birthday
                val monthOfBirth = Integer.parseInt(
                        dayOfBirth.substring(
                                dayOfBirth.indexOf("/") + 1,
                                dayOfBirth.lastIndexOf("/")
                        )
                )
                if (month == monthOfBirth) {
                    println("${student.name} - ${student.birthday}")
                }
            }
        } else {
            println("Month is validate!!!")
        }
    }

    private fun findStudentByMajor(students: List<Student>) {
        val major = Common.inputString("Nhập chuyên ngành: ")
        for (student in students) {
            if (student.major.contains(major)) {
                println(student)
            }
        }
    }

    private fun findStudentByClass(students: List<Student>) {
        val className = Common.inputString("Nhập tên lớp: ")
        for (student in students) {
            if (student.class_name.contains(className)) {
                println(student)
            }
        }
    }

    private fun findStudentByName(students: List<Student>) {
        val name = Common.inputString("Nhập tên cần tìm: ")
        for (student in students) {
            if (student.name.contains(name)) {
                println(student)
            }
        }
    }

    private fun sortStudent(students: List<Student>) {
        val sortBy = menu()
        val n = Common.input("Số lượng cần hiển thị: ")
        when (sortBy) {
            1 -> sortStudentByName(students, n)
            2 -> sortStudentByBirthDay(students, n)
            3 -> sortStudentByStId(students, n)
            4 -> sortStudentByDegreeNo(students, n)
            5 -> sortStudentByID(students, n)
        }
    }

    private fun menu(): Int {
        println(
                "1. Sort by name" +
                        "\n2. Sort by birthday" +
                        "\n3. Sort by student ID" +
                        "\n4. Sort by degreeNo" +
                        "\n5. Sort by list ID"
        )
        return Common.input("Nhập vào số tương ứng: ")
    }

    private fun sortStudentByID(students: List<Student>, n: Int) {
        val studentsSorted = students.sortedBy { it.id }
        if (n < studentsSorted.size) {
            for (i in 0 until n) {
                println(studentsSorted[i].id)
            }
        } else {
            studentsSorted.forEach(::println)
        }
    }

    private fun sortStudentByDegreeNo(students: List<Student>, n: Int) {
        val studentsSorted = students.sortedBy { it.degree_no }
        if (n < studentsSorted.size) {
            for (i in 0 until n) {
                println(studentsSorted[i].degree_no)
            }
        } else {
            studentsSorted.forEach { println(it.degree_no) }
        }
    }

    private fun sortStudentByStId(students: List<Student>, n: Int) {
        val studentsSorted = students.sortedBy { it.student_id }
        if (n < studentsSorted.size) {
            for (i in 0 until n) {
                println(studentsSorted[i].student_id)
            }
        } else {
            studentsSorted.forEach { println(it.student_id) }
        }
    }

    private fun sortStudentByBirthDay(students: List<Student>, n: Int) {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val studentsSorted = students.sortedBy { sdf.parse(it.birthday) }

        if (n < studentsSorted.size) {
            for (i in 0 until n) {
                println(studentsSorted[i].birthday)
            }
        } else {
            studentsSorted.forEach { println(it.birthday) }
        }
    }

    private fun sortStudentByName(students: List<Student>, n: Int) {
        val studentsSorted = students.sortedBy { it.name }.toList()
        if (n < studentsSorted.size) {
            for (i in 0 until n) {
                println(studentsSorted[i])
            }
        } else {
            studentsSorted.forEach(::println)
        }
    }

    private fun countStudentByRank(students: List<Student>) {
        val ranks = students.map { it.graduate_rank }.distinct().toList()
        for (rank in ranks) {
            println(rank + ": " + students.filter { it.graduate_rank == rank }.count())
        }
    }

    private fun filterStudentByMajor(students: List<Student>) {
        val major = Common.inputString("Nhập tên chuyên ngành: ")
        students.filter { it.major == major }.forEach(::println)
    }

    private fun countClass(students: List<Student>) {
        val classNames = students.map { it.class_name }.distinct()
        for (className in classNames) {
            println(className + ": " + students.filter { it.class_name == className }.count())
        }
    }

    private fun countStudent(students: List<Student>) {
        val majors = students.map { it.major }.distinct()
        for (major in majors) {
            println(major + ": " + students.filter { it.major == major }.count())
        }
    }

    private fun allMajor(students: List<Student>) {
        students.map { it.major }.distinct().forEach(::println)
    }

    private fun String.readFile(): List<Student> {
        var students: List<Student> = ArrayList()
        try {
            val file = File(this)
            println(file.name)
            val bufferedReader = file.bufferedReader()
            val inputString = bufferedReader.use { it.readText() }
            students = Gson().fromJson(inputString, Array<Student>::class.java).toList()
        } catch (e: FileNotFoundException) {
            println(e.message)
        }
        return students
    }
}
