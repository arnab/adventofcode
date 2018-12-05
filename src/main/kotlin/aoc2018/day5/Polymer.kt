package aoc2018.day5

data class PolymerUnit(val type: Char, val polarity: Boolean = type.isUpperCase())

object Polymer {

    fun reduce(units: List<PolymerUnit>, debug: Boolean = false): List<PolymerUnit> {
        var reducedUnits = units
        var pass = 1

        do {
            var reduced = false
            log(debug, "DEBUG: Pass $pass: ${reducedUnits.map(PolymerUnit::type).joinToString("")}")

            reducedUnits.forEachIndexed { i, unit ->
                val nextUnit = reducedUnits.getOrNull(i+1)
                if (!reduced && reacts(unit, nextUnit)) {
                    log(debug, "DEBUG: Pass: #$pass: Found reacting units: #$i: [${unit.type}, ${nextUnit?.type}].")
                    val previousUnits = reducedUnits.take(i)
                    val remainingUnits = reducedUnits.drop(i+2)
                    reducedUnits = previousUnits + remainingUnits
                    reduced = true
                }
            }
            pass++
        } while (reduced)

        return reducedUnits
    }

    fun reduceByRemovingBadUnits(units: List<PolymerUnit>, debug: Boolean = false): Pair<Char, List<PolymerUnit>> {
        val allTypes: List<Char> = units.map { it.type.toLowerCase() }.distinct().sorted()
        val reducedUnitsByRemovedType: List<Pair<Char, List<PolymerUnit>>> = allTypes.map { typeToRemove ->
            println("Removing $typeToRemove and reducing...")
            Pair(typeToRemove, reduce(removeUnitsOfType(units, typeToRemove)))
        }

        return reducedUnitsByRemovedType.minBy { it.second.size }!!
    }

    private fun removeUnitsOfType(units: List<PolymerUnit>, typeToRemove: Char): List<PolymerUnit> {
        return units.filter { it.type.toLowerCase() != typeToRemove.toLowerCase() }
    }

    private fun reacts(unit: PolymerUnit?, otherUnit: PolymerUnit?): Boolean {
        return unit != null && otherUnit != null &&
                unit.type.toLowerCase() == otherUnit.type.toLowerCase() &&
                unit.polarity != otherUnit.polarity
    }

    private fun log(debug: Boolean, message: String) {
        if(debug) println(message)
    }

}
