package aoc2020.day2

object BadPwd {

    data class PwdSpec(
        val range: IntRange,
        val char: Char,
        val chars: String
    )

    fun solve(data: List<PwdSpec>) = data.filter { isValidPassword(it) }.count()

    private fun isValidPassword(spec: PwdSpec): Boolean {
        val charCount = spec.chars.filter { it == spec.char }.count()
        return spec.range.contains(charCount)
    }

//    fun solve2(data: List<PwdSpec>): Int {
//        TODO("Not yet implemented")
//    }

}
