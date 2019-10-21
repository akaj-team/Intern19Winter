package asiantech.internship.summer.kotlincore.advanced

import com.google.gson.Gson
import java.io.File

fun readJsonFile(jsonFileName: String): MutableList<Student> {
  val jsonString: String = File(jsonFileName).readText(Charsets.UTF_8)
  lateinit var listStudent: MutableList<Student>
  val gson = Gson()
  listStudent = gson.fromJson(jsonString, Array<Student>::class.java).toMutableList()
  return listStudent
}