package com.congtrinh.Bai32;

public class Square extends Shape {
    private double edge;

    public Square(double edge) {
        this.edge = edge;
    }


    @Override
    public double area() {
        return edge*edge;
    }

    @Override
    public double circumference() {
        return edge*4;
    }
    @Override
    public void print() {
        System.out.print("Square ");
    }
}
