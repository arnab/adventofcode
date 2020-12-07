package aoc2020.day7

object LuggageProcessing {

    fun parse(data: String): Map<String, List<Pair<String, Int>>> =
        data.split("\n").map { line ->
            val(type, specs) = line.split("bags contain ")
            val containedBags = specs.replace(".", "")
                .split(", ")
                .mapNotNull { specPart -> parseBagSpec(specPart) }
            type.trim() to containedBags
        }.toMap()

    private val bagSpecPattern = Regex("""(\d+) (.*) bags?""")

    private fun parseBagSpec(spec: String): Pair<String, Int>? {
        val matches = bagSpecPattern.matchEntire(spec)
        val (count, type) = matches?.destructured ?: return null
        return Pair(type.trim(), count.toInt())
    }

    fun findBagsContaining(type: String, specs: Map<String, List<Pair<String, Int>>>): List<String> {
        val bagsContainingType = specs.filterValues { bagSpec ->
            bagSpec.any { (bagType, _) -> bagType == type }
        }.keys

        return (bagsContainingType + bagsContainingType.flatMap { findBagsContaining(it, specs) }).distinct()
    }

    fun findBagsContainedIn(type: String, specs: Map<String, List<Pair<String, Int>>>): List<String> {
        return emptyList()
    }

}