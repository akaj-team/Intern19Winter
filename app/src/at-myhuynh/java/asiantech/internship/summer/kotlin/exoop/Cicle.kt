package asiantech.internship.summer.kotlin.exoop

class Circle(private val r: Int) : Shape() {

    override fun perimeter(): Double {
        return r.toDouble() * 2.0 * Math.PI
    }

    override fun area(): Double {
        return r.toDouble() * r.toDouble() * Math.PI
    }
}
