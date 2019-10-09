package asiantech.internship.winter.javacore.oop;

public class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return 3.14 * radius * radius;
    }

    @Override
    public double circumference() {
        return 3.14 * radius * 2;
    }


    @Override
    public void print() {
        System.out.print("Circle ");
    }
}
