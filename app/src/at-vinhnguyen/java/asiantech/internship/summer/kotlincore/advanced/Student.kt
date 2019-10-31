package asiantech.internship.summer.kotlincore.advanced

import com.google.gson.annotations.SerializedName

data class Student(
        val id: Int,
        val name: String,
        val birthday: String,
        val hometown: String,
        @SerializedName("student_id") val studentId: String,
        @SerializedName("class_name") val className: String,
        val type: String,
        val major: String,
        @SerializedName("graduate_rank") val graduateRank: String,
        val title: String,
        @SerializedName("degree_no") val degreeNo: String,
        @SerializedName("document_id") val documentId: String) {

    override fun toString(): String {
        return "Id : $id\nName : $name\nBirthday : $birthday\nHometown : $hometown\nStudent Id : $studentId\n" +
                "Class Name : $className\nType : $type\nMajor : $major\nGraduate Rank : $graduateRank\n" +
                "Title : $title\nDegree No : $degreeNo\nDocument Id : $documentId\n"
    }
}
