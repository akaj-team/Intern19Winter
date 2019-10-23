package asiantech.internship.summer.java_core.exoop;

public class Square extends Shape {

    private int edge;

    public Square(int edge) {
        this.edge = edge;
    }

    @Override
    public double perimeter() {
        return edge * 4;
    }

    @Override
    public double area() {
        return edge * edge;
    }

    @Override
    public String print() {
        return "HV";
    }
}
