package com.congtrinh

import java.util.*

/*
Bài 18. Nhập một xâu ký tự. Đếm số từ của xâu ký tự đó. Thí dụ " Trường học " có 2 từ.
 */
object Bai18 {
    @JvmStatic
    fun main(args:Array<String>){
        val scanner=Scanner(System.`in`)
        print("Nhap vao mot chuoi: ")
        val str=scanner.nextLine()
        println("So tu co trong cau la: ${StringTokenizer(str," ").countTokens()}")
    }
}