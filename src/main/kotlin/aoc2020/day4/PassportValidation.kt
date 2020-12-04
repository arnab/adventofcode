package aoc2020.day4

object PassportValidation {
    private const val PASSPORT_EOF_MARKER = "<ENDOFPASSPORT>"

    data class Passport(
        val attrs: Map<String, String>
    ) {
        fun isValid() = attrs.keys.filter { it != "cid" }.count() == 7
    }

    fun parse(data: String): List<Passport> {
        val rawPassports = data.replace("(?m)^[ \t]*\r?\n".toRegex(), PASSPORT_EOF_MARKER)
            .replace("\n", " ")
            .split(PASSPORT_EOF_MARKER)
        return rawPassports.map {
            it.split(" ")
                .filter { fields -> fields.isNotBlank() }
                .map { field -> field.split(":") }
                .associateBy(
                    { attr -> attr.first() },
                    { attr -> attr.last() }
                )
                .let { attrs -> Passport(attrs) }
        }
    }

}
