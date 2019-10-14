package asiantech.internship.winter.javacore.oop

class Circle(private val radius: Double) : Shape() {

    public override fun area(): Double {
        return Math.PI * radius * radius
    }

    public override fun circumference(): Double {
        return Math.PI * radius * 2.0
    }


    public override fun print() {
        print("Circle ")
    }
}
