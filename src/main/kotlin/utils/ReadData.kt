package utils

import exceptions.EIncorrectValue

object ReadData {

    fun readString(): String {
        return readLine()!!
    }

    fun readString(regex: Regex): String {
        val line = readLine()
        if (line != null) {
            if (line.matches(regex))
                return line
        }
        throw EIncorrectValue("VALOR NO VALIDO")
    }

    fun readNumber(): Int {
        val line = readLine()
        if (line != null) {
            if (line.matches("\\d+".toRegex())) {
                return line.toInt()
            }
        }
        return -1
    }


}