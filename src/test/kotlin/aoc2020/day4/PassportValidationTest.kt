package aoc2020.day4

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class PassportValidationTest {

    private val testInput = """
        ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
        byr:1937 iyr:2017 cid:147 hgt:183cm

        iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
        hcl:#cfa07d byr:1929

        hcl:#ae17e1 iyr:2013
        eyr:2024
        ecl:brn pid:760753108 byr:1931
        hgt:179cm

        hcl:#cfa07d eyr:2025 pid:166559648
        iyr:2011 ecl:brn hgt:59in
        """.trimIndent()

    @Test
    fun `part 1 test`() {
        val passports = PassportValidation.parse(testInput)
        val countValid = passports.filter { it.isValid() }.count()
        Assertions.assertEquals(2, countValid)
    }

    @Test
    fun `part 1 real`() {
        val data = File("src/test/resources/aoc2020/day4/input.txt").readText()
        val passports = PassportValidation.parse(data)
        val countValid = passports.filter { it.isValid() }.count()
        Assertions.assertEquals(233, countValid)
    }

    @Test
    fun `part 2 real`() {
        val data = File("src/test/resources/aoc2020/day4/input.txt").readText()
        val passports = PassportValidation.parse(data)
        val countValid = passports.filter { it.isValidWithStricterRules() }.count()
        Assertions.assertEquals(111, countValid)
    }

}
