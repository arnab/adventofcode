package aoc2018.day3

data class Claim(
    val id: Int,
    val left: Int,
    val top: Int,
    val width: Int,
    val height: Int,
    val topLeftSquare: Square = Square(left, top),
    val bottomRightSquare: Square = Square(left + width - 1, top + height - 1)
) {
    fun includesSquare(square: Square): Boolean {
        return topLeftSquare.x <= square.x &&
                topLeftSquare.y <= square.y &&
                bottomRightSquare.x >= square.x &&
                bottomRightSquare.y >= square.y
    }
}

data class Square(val x: Int, val y: Int)

object FabricClaimCalculator {
    fun findConflictingSquares(claims: List<Claim>): List<Square> {
        val maxX = claims.maxBy { it.bottomRightSquare.x }!!.bottomRightSquare.x
        val maxY = claims.maxBy { it.bottomRightSquare.y }!!.bottomRightSquare.y

        val conflictingSquares = mutableListOf<Square>()

        for (x in 0..maxX) {
            for (y in 0..maxY) {
                val square = Square(x,y)
                val claimsIncludingSquare = claims.filter { it.includesSquare(square) }
                if (claimsIncludingSquare.size > 1) {
                    conflictingSquares.add(square)
                }
            }
        }

        return conflictingSquares.distinct()
    }

}
