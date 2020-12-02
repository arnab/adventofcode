package aoc2020.day2

object BadPwd {

    data class PwdSpec(
        val range: IntRange,
        val char: Char,
        val chars: String
    )

    fun solve(data: List<PwdSpec>) = data.filter { isValidPasswordByOldPolicy(it) }.count()

    private fun isValidPasswordByOldPolicy(spec: PwdSpec): Boolean {
        val charCount = spec.chars.filter { it == spec.char }.count()
        return spec.range.contains(charCount)
    }

    fun solve2(data: List<PwdSpec>)= data.filter { isValidPasswordByNewPolicy(it) }.count()

    private fun isValidPasswordByNewPolicy(spec: PwdSpec): Boolean {
        val charAtStartIndex = spec.chars[spec.range.first - 1]
        val charAtEndIndex = spec.chars[spec.range.last - 1]
        return listOf(charAtStartIndex, charAtEndIndex).filter { it == spec.char }.count() == 1
    }

}
