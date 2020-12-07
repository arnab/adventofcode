package aoc2020.day7

object LuggageProcessing {

    fun parse(data: String): Map<String, Map<String, Int>> =
        data.split("\n").map { line ->
            val(type, specs) = line.split("bags contain ")
            val containedBags = specs.replace(".", "")
                .split(", ")
                .mapNotNull { specPart -> parseBagSpec(specPart) }
                .associateBy({it.first}, {it.second})
            type to containedBags
        }.toMap()

    fun calculateDependencies(specs: Map<String, Map<String, Int>>): Map<String, List<String>> =
        specs.keys.fold(mapOf<String, List<String>>()) { acc, type ->
            flattenDependenciesRecursively(type, acc, specs)
        }.toMap()

    private fun flattenDependenciesRecursively(
        type: String,
        currentDependencies: Map<String, List<String>>,
        specs: Map<String, Map<String, Int>>
    ): Map<String, List<String>> {
        val containedBags = specs[type] ?: emptyMap()
        if (containedBags.isEmpty()) return currentDependencies

        val dependencies = containedBags.keys.map { containedType ->
            val knownDepdendentBags = currentDependencies[containedType]
            flattenDependenciesRecursively(containedType, )
        }

        return currentDependencies + dependencies
    }

    private val bagSpecPattern = Regex("""(\d+) (.*) bags?""")

    private fun parseBagSpec(spec: String): Pair<String, Int>? {
        val matches = bagSpecPattern.matchEntire(spec)
        val (count, type) = matches?.destructured ?: return null
        return Pair(type, count.toInt())
    }

}