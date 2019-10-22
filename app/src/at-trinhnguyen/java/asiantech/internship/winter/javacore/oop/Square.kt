package asiantech.internship.winter.javacore.oop

class Square(private val edge: Double) : Shape() {


    public override fun area(): Double {
        return edge * edge
    }

    public override fun circumference(): Double {
        return edge * 4
    }

    public override fun print() {
        print("Square ")
    }
}
