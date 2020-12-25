package aoc2020.day14

object BitMask {
    private val DEFAULT_MASK = Mask.from("X".repeat(36))

    private val memoryLocationPattern = Regex("""mem\[(\d+)]""")

    data class Mask(
        val bits: List<Int?>
    ) {
        companion object {
            fun from(mask: String) = mask.split("")
                .filterNot { it.isBlank() }
                .map { s ->
                    if (s == "X") null
                    else s.toInt()
                }.let { bits -> Mask(bits) }
        }
    }

    fun process(
        instructions: List<String>,
        mask: Mask = DEFAULT_MASK,
        memory: MutableMap<Int, Long> = mutableMapOf()
    ): Map<Int, Long> {
        if (instructions.isEmpty()) return memory

        val (code, data) = instructions.first().split(" = ")
        val remainingInstructions = instructions.drop(1)
        return when {
            code == "mask" -> process(remainingInstructions, Mask.from(data), memory)
            code.startsWith("mem") -> {
                val memoryLocation = memoryLocationPattern.matchEntire(code)!!.destructured.component1().toInt()
                memory[memoryLocation] = data.toLong().withMask(mask)
                process(remainingInstructions, mask, memory)
            }
            else -> throw IllegalArgumentException("Unknown code: $code!")
        }
    }

    fun part1(instructions: List<String>) = process(instructions).values.sum()
}

private fun Long.withMask(mask: BitMask.Mask): Long {
    val bitsStr = this.toString(2).padStart(36, '0')
    val bits = BitMask.Mask.from(bitsStr).bits
    val maskedBits = mask.bits.zip(bits).map { it.first ?: it.second }
    return maskedBits.joinToString("").toLong(2)
}
