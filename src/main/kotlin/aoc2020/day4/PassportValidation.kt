package aoc2020.day4

object PassportValidation {
    private const val PASSPORT_EOR_MARKER = "<ENDOFPASSPORT>"

    data class Passport(
        val attrs: Map<String, String>
    ) {
        fun isValid() = attrs.keys.filter { it != "cid" }.count() == 7

        fun isValidWithStricterRules(): Boolean {
            if (!isValid()) {
                return false
            }

            if (attrs["byr"]?.toInt() !in 1920..2002) {
                return false
            }

            if (attrs["iyr"]?.toInt() !in 2010..2020) {
                return false
            }

            if (attrs["eyr"]?.toInt() !in 2020..2030) {
                return false
            }

            val heightParts = Regex("""(\d+)((in)|(cm))""").matchEntire(attrs["hgt"].toString())
            val (height, unit) = heightParts?.destructured ?: return false
            when(unit) {
                "in" -> if (height.toInt() !in 59..76) return false
                "cm" -> if (height.toInt() !in 150..193) return false
                else -> return false
            }

            if (!Regex("""#([a-f]|\d){6}""").matches(attrs["hcl"].toString())) {
                return false
            }

            val knownEyeColors = "amb blu brn gry grn hzl oth".split(" ")
            if (!knownEyeColors.contains(attrs["ecl"])) {
                return false
            }

            if (!Regex("""\d{9}""").matches(attrs["pid"].toString())) {
                return false
            }

            return true
        }
    }

    fun parse(data: String): List<Passport> {
        // Replace empty lines with a EOR marker, and use that to split records
        val rawPassports = data.replace("(?m)^\n".toRegex(), PASSPORT_EOR_MARKER)
            .replace("\n", " ")
            .split(PASSPORT_EOR_MARKER)

        return rawPassports.map {
            it.split(" ")
                .filter { field -> field.isNotBlank() }
                .map { field -> field.split(":") }
                .associateBy(
                    { attr -> attr.first() },
                    { attr -> attr.last() }
                )
                .let { attrs -> Passport(attrs) }
        }
    }

}
