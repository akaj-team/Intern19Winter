package asiantech.internship.summer.kotlin.exoop

class Square(private val edge: Int) : Shape() {

    override fun perimeter(): Double {
        return (edge * 4).toDouble()
    }

    override fun area(): Double {
        return (edge * edge).toDouble()
    }

    override fun print(): String {
        return "HV"
    }
}
