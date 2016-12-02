package aoc.util

import java.io.File

object TestResourceReader {
    fun readFile(filename: String): String {
        return File(ClassLoader.getSystemResource(filename).toURI()).readText()
    }
}
