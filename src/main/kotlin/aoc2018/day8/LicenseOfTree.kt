package aoc2018.day8

object LicenseOfTree {

    data class Node(val children: List<Node>, val metadata: List<Int>) {

        companion object {
            fun of(values: Iterator<Int>): Node {
                val numChildren: Int = values.next()
                val numMetadata: Int = values.next()
                val children = (0 until numChildren).map { Node.of(values) }
                val metadata = (0 until numMetadata).map { values.next() }.toList()
                return Node(children, metadata)
            }
        }

        fun sumOfMetadata(): Int = metadata.sum() + children.sumBy(Node::sumOfMetadata)

    }

}
