package asiantech.internship.summer.java_core.exoop;

public class Circle extends Shape {

    private int r;

    public Circle(int r) {
        this.r = r;
    }

    @Override
    public double perimeter() {
        return r * 2 * Math.PI;
    }

    @Override
    public double area() {
        return r * r * Math.PI;
    }
}
