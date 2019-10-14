package asiantech.internship.winter.javacore.oop

import java.util.*

/*
Tạo ra 1 lớp Hinh(lớp cha) sau đó thực hiện các chức năng sau:
Thêm 1 ArrayList Hinh gồm HinhChuNhat,HinhTron,HinhVuong
Viết chương trình tính Diện tích, chu vi hình chữ nhật,hình Vuông trong ArrayList ở trên.
In ra kết quả trong ArrayList ở trên,nếu Hình chữ nhật thì suất ra Hinh Chữ Nhật,
Hình Tròn in ra hình tròn nếu không phải 2 hình trên thì in ra Default.
 */
object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val shapes = ArrayList<Shape>()
        shapes.add(Rectangle(3.0, 6.0))
        shapes.add(Rectangle(3.0, 3.0))
        shapes.add(Circle(3.0))
        shapes.add(Square(3.0))

        shapes.forEach {
            it.print()
            println(" S = ${it.area()} & P = ${it.circumference()}")
        }
    }
}
