package asiantech.internship.summer.KotlinW2Json

import android.util.Log.println

import com.google.gson.Gson
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val file = "C:\\Users\\My Computer\\AndroidStudioProjects\\Intern19Winter\\app\\src\\at-tamle\\java\\newjson\\data.json"
        var choose = true
        var students: List<Student> = ArrayList()
        students = readJsonFromFile(file)
        while (true)
            when (showMenu()) {
                1 -> showAllMajor(students)
                2 -> countStudent(students)
                3 -> countClass(students)
                4 -> allStudentsByMajor(students)
                5 -> countNumberStudentsByRank(students)
                6 -> sortStudent(students)
                7 -> findName(students)
                8 -> findStuentByClass(students)
                9 -> findStudentByMajor(students)
                10 -> inputMonthShowAllBirthday(students)
                else -> choose = false
            }
    }

    private fun showAllMajor(students: List<Student>) {
        println("hiển thị tất cả chuyên ngành:")
        students.map { it.major }.distinct().forEach { println(it) }

    }

    private fun countStudent(students: List<Student>) {
        val majors = students.map { it.major }.distinct()
        for (major in majors) {
            println(major + ": " + students.filter { it.major == major }.count())
        }
    }

    private fun countClass(students: List<Student>) {
        val classNames = students.map { it.class_name }.distinct()
        for (className in classNames) {
            println(className + " : " + students.filter { it.class_name == className }.count())
        }
    }

    private fun allStudentsByMajor(students: List<Student>) {
        val major = Com.inputString("nhập tên chuyên ngành: ")
        students.filter { it.major == major }.forEach { println(it) }

    }

    private fun countNumberStudentsByRank(students: List<Student>) {
        val ranks = students.map { it.graduate_rank }.distinct().toList()

        for (rank in ranks) {
            println(rank + ": " + students.filter { it.graduate_rank == rank }.count())
        }
    }

    private fun sortStudent(students: List<Student>) {
        val sortBy = menu()
        val n = Com.input("số lượng cần hiển thị: ")
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
                "1. Sort by name\n" +
                        "2. Sort by birthday\n" +
                        "3. Sort by student ID\n" +
                        "4. Sort by degreeNo\n" +
                        "5. Sort by list ID\n"
        )
        return Com.input("Nhập vào số tương ứng: ")
    }

    private fun sortStudentByID(students: List<Student>, n: Int) {
        val studentsSorted = students.sortedBy { it.id }
        if (n < studentsSorted.size) {
            for (i in 0 until n) {
                println(studentsSorted[i].id)
            }
        } else {
            studentsSorted.forEach { println(it) }
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
                println(studentsSorted[i].name)
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
            studentsSorted.forEach { println(it) }
        }
    }

    private fun findName(students: List<Student>) {
        val name = Com.inputString("nhập tên cần tìm: ")
        for (student in students) {
            if (student.name.contains(name)) {
                println(student)
            }
        }
    }

    private fun findStuentByClass(students: List<Student>) {
        val className = Com.inputString("nhập tên lớp: ")
        for (student in students) {
            if (student.class_name.contains(className)) {
                println(student)
            }
        }
    }

    private fun findStudentByMajor(students: List<Student>) {
        val major = Com.inputString("nhập chuyên ngành: ")
        for (student in students) {
            if (student.major.contains(major)) {
                println(student)
            }
        }
    }

    private fun inputMonthShowAllBirthday(students: List<Student>) {
        val month = Com.input("nhập vào tháng: ")
        if (month in 1..12) {
            for (student in students) {
                val dayOfBirth = student.birthday
                val monthOfBirth = Integer.parseInt(
                        dayOfBirth.substring(4, 5))
                if (month == monthOfBirth) {
                    println("${student.name} - ${student.birthday}")
                }
            }
        } else {
            println("nhập tháng sai (yêu cầu từ 1>12)")
        }
    }

    private fun readJsonFromFile(file: String): List<Student> {
        var students: List<Student> = ArrayList()
        val gson = Gson()
        val bufferedReader = File(file).bufferedReader()
        val inputString = bufferedReader.use { it.readText() }
        students = gson.fromJson(inputString, Array<Student>::class.java).toList()
        return students
    }


    private fun showMenu(): Int {
        println(
                "1.Show all the major on that list.\n" + "2.Count how many students in the inputted major.\n" +
                        "3.Count how many classes in the inputted major.\n" + "4.Input major, show all the students in that major.\n" +
                        "5.Count the number of students for each degree rank.\n" +
                        "6.Sort the student and show the first n (Input) students. (Create a menu sort by (name, birthday, student_id, degreeNo, id))\n" +
                        "7.Find students by name." + "8.Find students by class.\n" + "9.Find students by major.\n" + "10.Input the month, show all the students have birthday in that month.\n"
        )
        return Com.input("nhập số tương ứng")
    }
}
