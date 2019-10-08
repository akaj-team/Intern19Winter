package OOP;

public class Rectangle extends Shape {

    private float a, b;

    public Rectangle(float a, float b) {
        this.a = a;
        this.b = b;
    }

    @Override
    float Acreage() {
        return a * b;
    }

    @Override
    float Perimeter() {
        return (a + b) * 2;
    }

    @Override
    void printNameOfTheShape() {
        if (a == b) {
            super.printNameOfTheShape();
            return;
        }
        System.out.println("Rectangle:");
    }

}
