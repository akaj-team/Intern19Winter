package asiantech.internship.summer.kotlincore.advanced

import asiantech.internship.summer.kotlincore.inputIntNumber
import java.io.File
import java.util.*

object Menu {

    private lateinit var studentList: MutableList<Student>
    @JvmStatic
    fun main(args: Array<String>) {
        studentList = readJsonFile(
                File(
                        "app/src/at-vinhnguyen/java/asiantech/internship/summer/advanced/data.json").absolutePath)
        var choice: Int
        loop@ do {
            println(
                    "--------------------------------------------------------------------------\n" +
                            "1. Show all the major on that list.\n" +
                            "2. Count how many students in the inputted major.\n" +
                            "3. Count how many classes in the inputted major.\n" +
                            "4. Input major, show all the students in that major.\n" +
                            "5. Count the number of students for each degree rank.\n" +
                            "6. Sort the student and show the first n (Input) students. (Create a menu sort by (name, birthday, student_id, degreeNo, id))\n" +
                            "7. Find students by name.\n" +
                            "8. Find students by class.\n" +
                            "9. Find students by major.\n" +
                            "10. Input the month, show all the students have birthday in that month.\n" +
                            "11. Exit.\n" +
                            "--------------------------------------------------------------------------"
            )
            print("Input your choice:")
            choice = inputIntNumber()
            when (choice) {
                1 -> showAllMajor()
                2 -> countStudentInInputtedMajor()
                3 -> countClassInInputtedMajor()
                4 -> findStudentByMajor()
                5 -> countStudentEachDegreeRank()
                6 -> sortStudentAndShowStudent()
                7 -> findStudentByName()
                8 -> findStudentByClass()
                9 -> findStudentByMajor()
                10 -> showAllStudentHaveBirthdayInTheMonthInput()
                11 -> {
                    println("Thankyou!!!")
                    break@loop
                }
                else -> {
                    println("Your choice must be between 1 and 11!\nPlease try again!")
                }
            }
        } while (true)
    }

    private fun showAllStudentHaveBirthdayInTheMonthInput() {
        print("Input the month you want to find: ")
        val month = inputIntNumber()
        studentList.filter { it.birthday.substring(3, 5).toInt() == month }.forEach { println(it) }
    }

    private fun findStudentByMajor() {
        if (studentList.size == 0) {
            println("List Student is empty!!!")
            return
        }
        print("Input your major you want to find: ")
        val major = Scanner(System.`in`).nextLine()
        val subStudentListByName = studentList.filter { it.major == major }
        if (subStudentListByName.isNotEmpty()) {
            subStudentListByName.forEach { print(it) }
        } else {
            println("No results found")
        }
    }

    private fun findStudentByClass() {
        if (studentList.size == 0) {
            println("List Student is empty!!!")
            return
        }
        print("Input your class name you want to find: ")
        val className = Scanner(System.`in`).nextLine()
        val subStudentListByName = studentList.filter { it.className == className }
        if (subStudentListByName.isNotEmpty()) {
            subStudentListByName.forEach { print(it) }
        } else {
            println("No results found")
        }
    }

    private fun findStudentByName() {
        if (studentList.size == 0) {
            println("List Student is empty!!!")
            return
        }
        print("Input your student name you want to find: ")
        val name = Scanner(System.`in`).nextLine()
        val subStudentListByName = studentList.filter { it.name.contains(name) }
        if (subStudentListByName.isNotEmpty()) {
            subStudentListByName.forEach { print(it) }
        } else {
            println("No results found")
        }
    }

    //TODO Done
    private fun sortStudentAndShowStudent() {
        println(
                "You want to sort student list by:\n" + "1. Name\n" + "2. Birthday\n" + "3. Student Id\n" + "4. Degree No\n" + "5. Id")
        print("Your choice: ")
        when (inputIntNumber()) {
            1 -> {
                studentList.sortBy { it.name }
                studentList.forEach { println(it).toString() }
            }
            2 -> {
                studentList.sortBy { it.birthday }
                studentList.forEach { println(it).toString() }
            }
            3
            -> {
                studentList.sortBy { it.studentId }
                studentList.forEach { println(it).toString() }
            }
            4
            -> {
                studentList.sortBy { it.degreeNo }
                studentList.forEach { println(it).toString() }
            }
            5
            -> {
                studentList.sortBy { it.id }
                studentList.forEach { println(it).toString() }
            }
            else -> {
                println("Your choice must be between 1 and 5...\n")
            }
        }
    }

    private fun countStudentEachDegreeRank() {
        val numOccurrencesMap = studentList.groupingBy { it.graduateRank }.eachCount()
        numOccurrencesMap.forEach { println(it) }
    }

    private fun countClassInInputtedMajor() {
        if (studentList.size == 0) {
            println("List Student is empty!!!")
            return
        }
        println("Number of class in input major: ${studentList.groupBy { it.className }.count()}")
    }

    private fun countStudentInInputtedMajor() {
        if (studentList.size == 0) {
            println("List Student is empty!!!")
            return
        }
        println("Number of Student in inputted major: ${studentList.size}")
    }

    private fun showAllMajor() {
        if (studentList.size == 0) {
            println("List Student is empty!!!")
            return
        }
        println("All major in list:")
        studentList.map { it.major }.distinct().forEach { println(it) }
    }
}

