package com.congtrinh.Bai32;

public class Rectangle extends Shape {
    private double length ;
    private double width;

    @Override
    public void print() {
        if (length != width) {
            System.out.print("Rectangle ");
        }else super.print();
    }


    public Rectangle(double length, double width) {
        this.length  = length;
        this.width = width;
    }

    @Override
    public double area() {
        return length  * width;
    }

    @Override
    public double circumference() {
        return (length  + width) * 2;
    }
}
