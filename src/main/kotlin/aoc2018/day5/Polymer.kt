package aoc2018.day5

data class PolymerUnit(val type: Char, val polarity: Boolean = type.isUpperCase())

object Polymer {

    fun reduce(units: List<PolymerUnit>, pass: Int = 1): List<PolymerUnit> {
//        println("DEBUG: Pass $pass. Input: ${units.map(PolymerUnit::type).joinToString("")}")
        units.forEachIndexed { i, unit ->
            val nextUnit = units.getOrNull(i+1)
            if (reacts(unit, nextUnit)) {
//                println("DEBUG: Pass: #$pass: Found reacting units at #$i: [${unit.type}, ${nextUnit?.type}].")
                val previousUnits = units.take(i)
                val remainingUnits = units.drop(i+2)
                return reduce(previousUnits + remainingUnits, pass + 1)
            }
        }

        return units
    }

    private fun reacts(unit: PolymerUnit?, otherUnit: PolymerUnit?): Boolean {
        return unit != null && otherUnit != null &&
                unit.type.toLowerCase() == otherUnit.type.toLowerCase() &&
                unit.polarity != otherUnit.polarity
    }

}
