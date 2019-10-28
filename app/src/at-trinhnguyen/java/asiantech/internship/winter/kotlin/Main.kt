package asiantech.internship.winter.kotlin

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import java.io.FileReader
import java.text.SimpleDateFormat
import java.util.*

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
        val file = requireNotNull(this::class.java.classLoader?.getResource("data.json")?.file) { "File is not found!" }
        val students = gson.fromJson(FileReader(file), Array<Student>::class.java).toList()
        val scanner = Scanner(System.`in`)
        var menu: String
        var check = true
        printMenu()
        while (check) {
            print("Choose a function: ")
            menu = scanner.nextLine()
            when (menu) {
                "1" -> {
                    println(" All the major: ${students.groupBy { it.major }.keys}")
                }
                "2" -> {
                    println("Enter major: ")
                    val major = scanner.nextLine()
                    println("$major have ${students.filter { it.major == major }.count()} students")
                }
                "3" -> {
                    println("Enter major: ")
                    val major = scanner.nextLine()
                    println("$major have ${students.filter { it.major == major }.groupBy { it.className }.count()} classes")
                }
                "4" -> {
                    println("Enter major: ")
                    val major = scanner.nextLine()
                    println("All the student in $major: ")
                    students.filter { it.major == major }.forEach { println(it) }
                }
                "5" -> {
                    println("The number of students for each degree rank: ")
                    students.groupBy { it.graduateRank }
                            .forEach { (key, value) ->
                                println("$key : ${value.count()}")
                            }
                }
                "6" -> {
                    print("Enter n(first n students): ")
                    val n = Integer.parseInt(scanner.nextLine())
                    println("Sort by: 1. name 2. birthday 3. studentId 4. degreeNo 5. id")
                    when (scanner.nextLine()) {
                        "1" -> {
                            students.sortedBy { it.name }
                                    .subList(0, n)
                                    .forEach { println(it) }
                        }
                        "2" -> {
                            students.sortedBy { SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(it.birthday) }
                                    .subList(0, n)
                                    .forEach { println(it) }
                        }
                        "3" -> {
                            students.sortedBy { it.studentId }
                                    .subList(0, n)
                                    .forEach { println(it) }
                        }
                        "4" -> {
                            students.sortedBy { it.degreeNo }
                                    .subList(0, n)
                                    .forEach { println(it) }
                        }
                        "5" -> {
                            students.sortedBy { it.id }
                                    .subList(0, n)
                                    .forEach { println(it) }
                        }
                    }
                }
                "7" -> {
                    println("Enter student's name to find: ")
                    val name = scanner.nextLine()
                    if (students.any { it.name == name }) {
                        students.filter { it.name == name }.forEach { println(it) }
                    } else {
                        println("No student have name: $name")
                    }
                }
                "8" -> {
                    println("Enter student's class to find: ")
                    val className = scanner.nextLine()
                    if (students.any { it.className == className }) {
                        students.filter { it.className == className }.forEach { println(it) }
                    } else {
                        println("No student have class name: $className")
                    }
                }
                "9" -> {
                    println("Enter student's major to find: ")
                    val major = scanner.nextLine()
                    if (students.any { it.major == major }) {
                        students.filter { it.major == major }.forEach { println(it) }
                    } else {
                        println("No student have major: $major")
                    }
                }
                "10" -> {
                    print("Enter month: ")
                    val m = scanner.nextLine()
                    students.filter {
                        val date = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(it.birthday)
                        val calendar = Calendar.getInstance()
                        calendar.time = requireNotNull(date)
                        calendar.get(Calendar.MONTH).plus(1) == m.toInt()
                    }.forEach { println(it) }
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
