package asiantech.internship.summer.kotlin

private fun base(n: Int, base: Int) {
    if (n >= base) base(n / base, base)
    if (n % base > 9)
        System.out.printf("%c", n % base + 55)
    else
        print(n % base)
}

fun main() {
    print("Nhap n :")
    val n = input()
    print("Nhap vao co so can chuyen sang b :")
    val b = input()
    print("So $n chuyen sang co so $b thanh: ")
    base(n, b)
}
