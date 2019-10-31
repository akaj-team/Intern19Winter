package asiantech.internship.summer.kotlincore.exercise31

import java.io.*

object Exercise31 {

    @Throws(FileNotFoundException::class, IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {

        val f = File("./Bai31.txt")
        lateinit var builder: StringBuilder
        BufferedReader(FileReader(f)).use { bufferReader ->
            builder = StringBuilder()
            var line: String? = bufferReader.readLine()
            while (line != null) {
                builder.append(line)
                builder.append(System.lineSeparator())
                line = bufferReader.readLine()
            }
        }
        initArray(builder)

    }

    private fun initArray(str: StringBuilder) {
        val a = str.substring(str.indexOf("[") + 1, str.indexOf("]", str.indexOf("[")))
        val b = str.substring(str.indexOf("[", str.indexOf(a) + a.length + 1) + 1, str.lastIndexOf("]"))
        val A = standardized(a)
        val B = standardized(b)
        intersection(A, B)
        synthesis(A, B)
        difference(A, B)
    }

    private fun standardized(str: String): MutableList<String> {
        val a = ArrayList<String>()
        for (strspl in str.split(", ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) {
            a.add(strspl.substring(1, strspl.length - 1))
        }
        return a
    }

    private fun intersection(A: List<String>, B: List<String>) {
        val C = ArrayList<String>()
        for (A1 in A) {
            for (B1 in B) {
                if (A1 == B1) {
                    C.add(A1)
                }
            }
        }
        print("C = A∪B : ")
        for (C1 in C) {
            print(" $C1")
        }
        println("\n")
    }

    private fun synthesis(A: List<String>, B: List<String>) {
        val C = ArrayList<String>()
        C.addAll(B)
        for (subA in A) {
            var duplicate = false
            for (subB in B) {
                if (subB == subA) {
                    duplicate = true
                    break
                }
            }
            if (!duplicate) {
                C.add(subA)
            }
        }
        print("C = A∩B : ")
        for (C1 in C) {
            print(" $C1")
        }
        println("\n")
    }

    private fun difference(A: List<String>, B: List<String>) {
        val C = ArrayList<String>()
        for (subA in A) {
            var duplicate = false
            for (subB in B) {
                if (subB == subA) {
                    duplicate = true
                    break
                }
            }
            if (!duplicate) {
                C.add(subA)
            }
        }
        print("C = A\\B : ")
        for (C1 in C) {
            print(" $C1")
        }
        println("\n")
    }
}
