import java.util.*

/*
Bài 2. Viết chương trình chuyển đổi một số tự nhiên ở
hệ cơ số 10 thành số ở hệ cơ số b bất kì (1< b≤ 36).
*/
object Bai2 {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        print("Enter n: ")
        val n = scanner.nextInt()
        println("Enter base: ")
        val base = scanner.nextInt()
        println("Number $n from base 10 to base $base is : ")
        baseConvert(n, base)
    }

    private fun baseConvert(n: Int, base: Int) {
        if (n > base) baseConvert(n / base, base)
        if (n % base > 9) print(String.format("%c", n % base + 65 - 10))
        else print(n % base)
    }
}

