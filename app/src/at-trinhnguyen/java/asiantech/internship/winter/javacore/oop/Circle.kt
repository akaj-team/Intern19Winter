package asiantech.internship.winter.javacore.oop

class Circle(private val radius: Double) : Shape() {

    public override fun area(): Double {
        return 3.14 * radius * radius
    }

    public override fun circumference(): Double {
        return 3.14 * radius * 2.0
    }


    public override fun print() {
        print("Circle ")
    }
}
