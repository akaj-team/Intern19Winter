package asiantech.internship.winter.javacore.oop

class Rectangle(private val length: Double, private val width: Double) : Shape() {

    public override fun print() {
        if (length != width) {
            print("Rectangle ")
        } else
            super.print()
    }

    public override fun area(): Double {
        return length * width
    }

    public override fun circumference(): Double {
        return (length + width) * 2
    }
}
