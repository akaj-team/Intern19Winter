package asiantech.internship.winter.javacore.oop;

public class Rectangle extends Shape {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public void print() {
        if (length != width) {
            System.out.print("Rectangle ");
        } else super.print();
    }

    @Override
    public double area() {
        return length * width;
    }

    @Override
    public double circumference() {
        return (length + width) * 2;
    }
}
