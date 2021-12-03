package aoc2021.day3

object Day3 {
    fun parse(data: String) = data.split("\n")
        .map { readings -> readings.split("").filterNot { it.isBlank() } }

    fun calculatePowerConsumption(readings: List<List<String>>): Int =
        calculateGamma(readings) * calculateEpsilon(readings)

    private fun calculateGamma(readings: List<List<String>>): Int {
        val bits = readings.first().mapIndexed { i, _ ->
            readings.map { it[i] }.groupingBy { it }.eachCount().maxBy { it.value }!!.key
        }.joinToString("")
        return Integer.parseInt(bits, 2)
    }

    private fun calculateEpsilon(readings: List<List<String>>): Int {
        val bits = readings.first().mapIndexed { i, _ ->
            readings.map { it[i] }.groupingBy { it }.eachCount().minBy { it.value }!!.key
        }.joinToString("")
        return Integer.parseInt(bits, 2)
    }

    fun calculateLifeSupport(readings: List<List<String>>) =
        calculateO2Rating(readings, index = 0) * calculateCO2Rating(readings, index = 0)

    private fun calculateO2Rating(readings: List<List<String>>, index: Int): Int {
        if (readings.size == 1) {
            return Integer.parseInt(readings.first().joinToString(""), 2)
        }

        val bitCounts = readings.map { it[index] }.groupingBy { it }.eachCount()
        val filterBit = if (bitCounts["0"] == bitCounts["1"]) "1" else bitCounts.maxBy { it.value }!!.key

        val remainingReadings = readings.filter { it[index] == filterBit }

        return calculateO2Rating(remainingReadings, index + 1)
    }

    private fun calculateCO2Rating(readings: List<List<String>>, index: Int): Int {
        if (readings.size == 1) {
            return Integer.parseInt(readings.first().joinToString(""), 2)
        }

        val bitCounts = readings.map { it[index] }.groupingBy { it }.eachCount()
        val filterBit = if (bitCounts["0"] == bitCounts["1"]) "0" else bitCounts.minBy { it.value }!!.key

        val remainingReadings = readings.filter { it[index] == filterBit }

        return calculateCO2Rating(remainingReadings, index + 1)
    }
}
