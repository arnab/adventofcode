package aoc2018.day3

data class Claim(
    val id: Int,
    val left: Int,
    val top: Int,
    val width: Int,
    val height: Int,
    val topLeftSquare: Square = Square(left, top),
    val bottomRightSquare: Square = Square(left + width - 1, top + height - 1)
)

data class Square(val x: Int, val y: Int)

object FabricClaimCalculator {
    fun findConflictingSquares(claims: List<Claim>): List<Square> {
        val maxX = claims.maxBy { it.bottomRightSquare.x }!!.bottomRightSquare.x
        val maxY = claims.maxBy { it.bottomRightSquare.y }!!.bottomRightSquare.y

        TODO("")
    }

}
