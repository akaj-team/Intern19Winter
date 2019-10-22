package asiantech.internship.summer.java_core.exoop;

public class Rectangle extends Shape {

    private int length;
    private int width;

    public Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double perimeter() {
        return (length + width) * 2;
    }

    @Override
    public double area() {
        return length * width;
    }

    @Override
    public String print() {
        if (length == width) {
            return "HV";
        } else {
            return "HCN";
        }
    }
}
