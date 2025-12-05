private const val LOG_ALL = false

object Day05TestData {
    val INPUT: List<String> = listOf(
        "3-5",
        "10-14",
        "16-20",
        "12-18",
        "",
        "1",
        "5",
        "8",
        "11",
        "17",
        "32",
    )
    const val EXPECTED_PART_1_RESULT: Long = 3L
    const val EXPECTED_PART_2_RESULT: Long = 14L
}

enum class Mode { Ranges, Products }

data class State(
    var mode: Mode = Mode.Ranges,
    val freshRanges: MutableList<Pair<Long, Long>> = mutableListOf(),
    var counter: Long = 0L,
)

fun main() {
    fun part1(input: List<String>) = input.fold(State()) { acc, input ->
        when {
            input.isEmpty() -> acc.mode = Mode.Products
            acc.mode == Mode.Ranges -> acc.freshRanges.addRange(input)
            acc.mode == Mode.Products -> if (acc.freshRanges.containsProduct(input)) acc.counter += 1
        }
        acc
    }.counter

    fun part2(input: List<String>) = input.fold(State()) { acc, input ->
        log(input)
        when {
            input.isEmpty() -> return sumRanges(acc.freshRanges)
            acc.mode == Mode.Ranges -> acc.freshRanges.addRange(input)
            else -> error("WTF")
        }
        acc
    }.counter

    val testInput = Day05TestData.INPUT
    check(part1(testInput) == Day05TestData.EXPECTED_PART_1_RESULT)
    check(part2(testInput) == Day05TestData.EXPECTED_PART_2_RESULT)

    // Read the input from the `src/Day04.txt` file.
    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}

val ingredientsRangesComparator: (Pair<Long, Long>, Pair<Long, Long>) -> Int = { candidate, element ->
    when {
        element.first in candidate.first..candidate.second -> 0
        element.second in candidate.first..candidate.second -> 0
        element.first < candidate.first -> 1
        element.first > candidate.second -> -1
        else -> TODO()
    }
}

fun MutableList<Pair<Long, Long>>.addRange(rangeDef: String, forceLog: Boolean = false) {
    require(rangeDef.contains("-")) { "Incorrect input $rangeDef" }
    val range = rangeDef.split("-").let { it[0].toLong() to it[1].toLong() }
    log("current: $this", forceLog)
    log("add $range", forceLog)

    val index = binarySearch(
        range,
        comparator = { candidate, element ->
            log("$candidate : $element", forceLog)
            ingredientsRangesComparator(candidate, element)
        })
    log("index: $index", forceLog)

    if (index < 0) {
        val targetIndex = -index - 1
        val prev = getOrNull(targetIndex - 1)
        val next = getOrNull(targetIndex)
        log("$prev [...] $next", forceLog)

        when {
            prev != null && (prev.second + 1 >= range.first) -> {
                // adjust prev
                val corrected = range.first.coerceAtMost(prev.first) to prev.second.coerceAtLeast(range.second)
                removeAt(targetIndex - 1)
                addRange("${corrected.first}-${corrected.second}")
            }

            next != null && (next.first - 1 <= range.second) -> {
                // adjust next
                val corrected = next.first.coerceAtMost(range.first) to range.second.coerceAtLeast(next.second)
                removeAt(targetIndex)
                addRange("${corrected.first}-${corrected.second}")
            }

            else -> add(targetIndex, range)
        }
    } else {
        val matching = this[index]
        log("matching: $matching", forceLog)
        val corrected = matching.first.coerceAtMost(range.first) to matching.second.coerceAtLeast(range.second)
        removeAt(index)
        log("corrected: $corrected", forceLog)
        addRange("${corrected.first}-${corrected.second}")
    }
    log("final: $this", forceLog)
}

fun MutableList<Pair<Long, Long>>.containsProduct(productId: String): Boolean {
    val numericId = productId.toLong()
    return any { numericId >= it.first && numericId <= it.second }
}

fun sumRanges(ranges: List<Pair<Long, Long>>, forceLog: Boolean = false): Long {
    return ranges.fold(0L) { acc, input ->
        log("" + input, forceLog)
        val x = (input.second + 1 - input.first)
        log("inc: $x", forceLog)
        acc + (input.second + 1 - input.first)
    }
}

@Suppress("SimplifyBooleanWithConstants")
private fun log(s: String, forceLog: Boolean = false) =
    if (LOG_ALL || forceLog) println(s) else Unit
