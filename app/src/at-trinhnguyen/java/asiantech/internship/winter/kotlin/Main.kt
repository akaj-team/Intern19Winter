package asiantech.internship.winter.kotlin

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
        val file = "C:\\Users\\nguye\\AndroidStudioProjects\\Intern19Winter\\app\\src\\at-trinhnguyen\\java\\asiantech\\internship\\winter\\kotlin\\data.json"
        val students = gson.fromJson(FileReader(file), Array<Student>::class.java).toList()
        val majors = students.groupBy { it.major }
        val scanner = Scanner(System.`in`)
        var menu: String
        var check = true
        printMenu()
        while (check) {
            print("Choose a function: ")
            menu = scanner.nextLine()
            when (menu) {
                "1" -> {
                    println(" All the major: ${majors.keys}")
                }
                "2" -> {
                    majors.forEach { (key, value) ->
                        println("$key have ${value.count()} students")
                    }
                }
                "3" -> {
                    majors.forEach { (key, value) ->
                        println("$key have ${value.groupBy { it.class_name }.count()} class")
                    }
                }
                "4" -> {
                    println("Enter major: ")
                    val major = scanner.nextLine()
                    println("All the student in $major: ")
                    majors.filter { it.key == major }
                            .forEach { (_, value) ->
                                print(value.forEach {
                                    println(it.name)
                                })
                            }
                }
                "5" -> {
                    println("The number of students for each degree rank: ")
                    students.groupBy { it.graduate_rank }
                            .forEach { (key, value) ->
                                println("$key : ${value.count()}")
                            }
                }
                "6" -> {
                    print("Enter n(first n students): ")
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
                    println("Enter student's name to find: ")
                    val name = scanner.nextLine()
                    println(students.filter { it.name == name })
                }
                "8" -> {
                    println("Enter student's class to find: ")
                    val className = scanner.nextLine()
                    println(students.filter { it.class_name == className })
                }
                "9" -> {
                    println("Enter student's major to find: ")
                    val major = scanner.nextLine()
                    println(students.filter { it.major == major })
                }
                "10" -> {
                    print("Enter month: ")
                    val m = scanner.nextLine()
                    println(students.filter {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            LocalDate.parse(it.birthday, DateTimeFormatter.ofPattern("dd/MM/yyyy")).month.value == m.toInt()
                        } else {
                            val date = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(it.birthday)
                            date?.month?.plus(1) == m.toInt()
                        }
                    })
                }
                "Q", "q" -> {
                    check = false
                }
            }
        }
    }

    private fun printMenu() {
        println("1. Show all the major on that list.\n" +
                "2. Count how many students in the inputted major.\n" +
                "3. Count how many classes in the inputted major.\n" +
                "4. Input major, show all the students in that major.\n" +
                "5. Count the number of students for each degree rank.\n" +
                "6. Sort the student and show the first n (Input) students.\n" +
                "7. Find students by name.\n" +
                "8. Find students by class.\n" +
                "9. Find students by major.\n" +
                "10. Show all the students have birthday in month.\n" +
                "Q or q to quit")
    }
}
