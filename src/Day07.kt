private const val LOG_ALL = false

object Day07TestData {
    val INPUT: List<String> = listOf(
        ".......S.......",
        "...............",
        ".......^.......",
        "...............",
        "......^.^......",
        "...............",
        ".....^.^.^.....",
        "...............",
        "....^.^...^....",
        "...............",
        "...^.^...^.^...",
        "...............",
        "..^...^.....^..",
        "...............",
        ".^.^.^.^.^...^.",
        "...............",
    )
    const val EXPECTED_PART_1_RESULT = 21
    const val EXPECTED_PART_2_RESULT = 40
}

fun main() = with(Logger(logAll = false)) {
    fun part1(input: List<String>) = input.fold(TachyonMinifoldState()) { acc, string ->
        acc.applyInput(string)
    }.splits

    fun part2(input: List<String>) = input.fold(0) { acc, input ->
        log(input)
        acc
    }

    val testInput = Day07TestData.INPUT
    check(part1(testInput) == Day07TestData.EXPECTED_PART_1_RESULT)
    check(part2(testInput) == Day07TestData.EXPECTED_PART_2_RESULT)

    // Read the input from the `src/Day07.txt` file.
    val input = readInput("Day07")
    part1(input).println()
    part2(input).println()
}

data class TachyonMinifoldState(
    val splits: Int = 0,
    // in which columns tachyon beams exists
    val beams: Set<Int> = emptySet(),
)

fun TachyonMinifoldState.applyInput(string: String): TachyonMinifoldState {
    var specialCharacterIndex = string.indexOfFirst { it != '.' }
    var beams = beams
    var newSplits = 0
    while (specialCharacterIndex != -1) {
        val specialCharacter = string[specialCharacterIndex]
        beams = when (specialCharacter) {
            'S' -> beams.plus(specialCharacterIndex)
            '^' -> {
                if (this.beams.contains(specialCharacterIndex)) newSplits++
                beams.minus(specialCharacterIndex)
                    .plus(specialCharacterIndex - 1)
                    .plus(specialCharacterIndex + 1)
            }

            else -> error("Unexpected character '$specialCharacter'")
        }
        val nextSpecialIndex = string.drop(specialCharacterIndex + 1).indexOfFirst { it != '.' }
        if (nextSpecialIndex != -1) {
            specialCharacterIndex += 1 + nextSpecialIndex
        } else specialCharacterIndex = -1
    }
    return TachyonMinifoldState(splits + newSplits, beams.toSortedSet())
}

interface LoggerContext {
    fun log(any: Any, forceLog: Boolean = false)
}

class Logger(private val logAll: Boolean = false) : LoggerContext {
    override fun log(any: Any, forceLog: Boolean) {
        if (logAll || forceLog) println("$any") else Unit
    }
}