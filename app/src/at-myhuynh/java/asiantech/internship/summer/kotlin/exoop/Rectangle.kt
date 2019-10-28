package asiantech.internship.summer.kotlin.exoop

class Rectangle(val length: Int, val width: Int) : Shape() {

    override fun perimeter(): Double {
        return ((length + width) * 2).toDouble()
    }

    override fun area(): Double {
        return (length * width).toDouble()
    }

    override fun print(): String {
        return if (length == width) {
            "HV"
        } else {
            "HCN"
        }
    }
}
