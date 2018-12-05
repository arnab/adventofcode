package aoc2018.day5

import java.util.*

object PolymerOptimized {

    fun reduce(units: String): Int {
        val reducedUnits = Stack<Char>()

        units.forEach {currentUnit ->
            if (reducedUnits.empty()) {
                reducedUnits.push(currentUnit)
            } else {
                val previousUnit = reducedUnits.peek()
                if (reacts(previousUnit, currentUnit)) {
                    reducedUnits.pop()
                } else {
                    reducedUnits.push(currentUnit)
                }
            }
        }

        return reducedUnits.size
    }

    private fun reacts(unit: Char, otherUnit: Char): Boolean {
        return unit.toLowerCase() == otherUnit.toLowerCase() && unit != otherUnit
    }

    fun reduceByRemovingTypes(units: String): Pair<Char, Int> {
        val allTypes: List<Char> = units.map { it.toLowerCase() }.distinct().sorted()
        val reducedUnitsByRemovedType: List<Pair<Char, Int>> = allTypes.map { typeToRemove ->
            println("Removing $typeToRemove and reducing...")
            Pair(typeToRemove, reduce(removeUnitsOfType(units, typeToRemove)))
        }

        return reducedUnitsByRemovedType.minBy { it.second }!!
    }

    private fun removeUnitsOfType(units: String, typeToRemove: Char): String {
        val regex = typeToRemove.toString().toRegex(RegexOption.IGNORE_CASE)
        return units.replace(regex, "")
    }

}