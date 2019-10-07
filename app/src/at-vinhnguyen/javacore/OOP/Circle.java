
package OOP;

public class Circle extends Shape{
    
    private float r;
    private final float PI = 3.14f;
    public Circle(float r) {
        this.r = r;
    }
    
    @Override
    float Acreage() {
        return PI*r*r;
    }

    @Override
    float Perimeter() {
        return r*2*PI;
    }

    @Override
    void printNameOfTheShape() {
        System.out.println("Circle:");
    }
}
