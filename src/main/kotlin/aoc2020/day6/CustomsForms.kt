package aoc2020.day6

object CustomsForms {

    data class Group(
        val people: List<Person>
    ) {
        fun allAffirmativeAnswers() = people.flatMap { it.affirmativeAnswers }.distinct()

        fun combinedAffirmativeAnswers() = people.flatMap { it.affirmativeAnswers }
            .groupingBy { it }.eachCount()
            .filter { countForChar -> countForChar.value == people.size }
            .keys.toList()
    }

    data class Person(
        val affirmativeAnswers: List<Char>
    )

    fun parse(input: String): List<Group> =
        input.split("\n\n")
            .map { groupData ->
                Group(
                    groupData.split("\n")
                        .map { responses ->
                            Person(responses.toCharArray().toList())
                        }
                )
            }


}