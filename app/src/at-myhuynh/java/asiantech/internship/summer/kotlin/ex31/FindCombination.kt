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

        val c = a.interSection(b)
        val d = a.union(b)
        val e = a.difference(b)

        println("Giao: $c")
        println("Hợp: $d")
        println("Hiệu: $e")
    }
}

fun List<String>.interSection(b: List<String>): List<String> {
    val c = ArrayList<String>()
    for (itemB in b) {
        if (this.contains(itemB)) {
            c.add(itemB)
        }
    }
    return c
}

fun List<String>.union(b: List<String>): List<String> {
    val c = ArrayList(this)
    for (itemB in b) {
        if (!this.contains(itemB)) {
            c.add(itemB)
        }
    }
    return c
}

fun List<String>.difference(b: List<String>): List<String> {
    val c = ArrayList(this)
    for (itemB in b) {
        if (this.contains(itemB)) {
            c.remove(itemB)
        }
    }
    return c
}
