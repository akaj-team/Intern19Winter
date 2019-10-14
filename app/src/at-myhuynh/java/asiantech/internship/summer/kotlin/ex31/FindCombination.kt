package asiantech.internship.summer.kotlin.ex31

import java.util.ArrayList

object FindCombination {

    @JvmStatic
    fun main(args: Array<String>) {
        val a = ArrayList<String>()
        a.add("Lan")
        a.add("Hằng")
        a.add("Minh")
        a.add("Thuỷ")

        val b = ArrayList<String>()
        b.add("Nghĩa")
        b.add("Trung")
        b.add("Minh")
        b.add("Thuỷ")
        b.add("Đức")

        println("A: $a")
        println("B: $b")

        val c = interSection(a, b)
        val d = union(a, b)
        val e = difference(a, b)

        println("Giao: $c")
        println("Hợp: $d")
        println("Hiệu: $e")

    }

    private fun difference(a: List<String>, b: List<String>): List<String> {
        val c = ArrayList(a)
        for (itemB in b) {
            if (a.contains(itemB)) {
                c.remove(itemB)
            }
        }
        return c
    }

    private fun union(a: List<String>, b: List<String>): List<String> {
        val c = ArrayList(a)
        for (itemB in b) {
            if (!a.contains(itemB)) {
                c.add(itemB)
            }
        }
        return c
    }

    private fun interSection(a: List<String>, b: List<String>): List<String> {
        val c = ArrayList<String>()
        for (itemB in b) {
            if (a.contains(itemB)) {
                c.add(itemB)
            }
        }
        return c
    }
}
