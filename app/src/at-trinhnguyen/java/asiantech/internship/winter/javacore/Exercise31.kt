package asiantech.internship.winter.javacore

import java.util.*

/*
Bài 31. Cho hai tập hợp A gồm n phần tử, B gồm m phần tử (n,m≤255), mỗi phần tử của nó là một xâu kí tự.Ví dụ A = {“Lan”, “Hằng”, “Minh”, “Thủy”}, B = {“Nghĩa”, “Trung”, “Minh”, “Thủy”, “Đức”}. Hãy viết chương trình thực hiện những thao tác sau:
Tạo lập dữ liệu cho A và B (từ file hoặc từ bàn phím)
Tìm C = A∪B = { t : t∈A hoặc t∈B}.
Tìm C = A∩B = {t : t∈A và t ∈B}.
Tìm C = A\B = {t : t∈A và t ∉B}.
 */
object Exercise31 {
    @JvmStatic
    fun main(args: Array<String>) {
        val setA = ArrayList<String>()
        setA.add("1")
        setA.add("2")
        setA.add("3")
        setA.add("4")

        val setB = ArrayList<String>()
        setB.add("3")
        setB.add("4")
        setB.add("5")
        setB.add("6")
        setB.add("7")

        println("A: $setA")
        println("B: $setB")
        println("Intersection: ${intersection(setA, setB)}")
        println("Union: ${union(setA, setB)}")
        println("Subtraction: ${subtraction(setA, setB)}")

    }

    private fun intersection(A: List<String>, B: List<String>): List<String> {
        val setC = ArrayList<String>()
        for (itemB in B) {
            if (A.contains(itemB)) {
                setC.add(itemB)
            }
        }
        return setC
    }

    private fun union(A: List<String>, B: List<String>): List<String> {
        val setC = ArrayList<String>()
        setC.addAll(A)
        for (itemB in B) {
            if (!A.contains(itemB)) {
                setC.add(itemB)
            }
        }
        return setC
    }

    private fun subtraction(A: List<String>, B: List<String>): List<String> {
        val setC = ArrayList<String>()
        setC.addAll(A)
        for (itemB in B) {
            if (A.contains(itemB)) {
                setC.remove(itemB)
            }
        }
        return setC
    }
}
