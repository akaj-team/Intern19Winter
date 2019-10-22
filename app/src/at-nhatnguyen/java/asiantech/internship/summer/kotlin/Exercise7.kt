package asiantech.internship.summer.kotlin
fun main() {
    val shareds = Shared()
    print("Nhap n : ")
    val n = shareds.input()
    val f = IntArray(n + 1)
    f[0] = 1
    f[1] = 1
    for (i in 2..n) {
        f[i] = f[i - 1] + f[i - 2]
    }
    print("So Fibonanci thu " + n + " la: f[" + n + "]= " + f[n])
}
