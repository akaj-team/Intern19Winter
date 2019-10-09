package asiantech.internship.winter.javacore.oop;

import java.util.ArrayList;

/*
Tạo ra 1 lớp Hinh(lớp cha) sau đó thực hiện các chức năng sau:
Thêm 1 ArrayList Hinh gồm HinhChuNhat,HinhTron,HinhVuong
Viết chương trình tính Diện tích, chu vi hình chữ nhật,hình Vuông trong ArrayList ở trên.
In ra kết quả trong ArrayList ở trên,nếu Hình chữ nhật thì suất ra Hinh Chữ Nhật,
Hình Tròn in ra hình tròn nếu không phải 2 hình trên thì in ra Default.
 */
public class Bai32 {
    public static void main(String[] args) {
        ArrayList<Shape> shapes = new ArrayList<Shape>();
        shapes.add(new Rectangle(3, 6));
        shapes.add(new Rectangle(3, 3));
        shapes.add(new Circle(3));
        shapes.add(new Square(3));

        for (int i = 0; i < shapes.size(); i++) {

            shapes.get(i).print();
            System.out.println(" S= "
                    + shapes.get(i).area() + " & P= " + shapes.get(i).circumference());

        }
    }
}

