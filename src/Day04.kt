private const val LOG_ALL = false

object Day04TestData {
    val INPUT: List<String> = listOf(
        "..@@.@@@@.",
        "@@@.@.@.@@",
        "@@@@@.@.@@",
        "@.@@@@..@.",
        "@@.@@@@.@@",
        ".@@@@@@@.@",
        ".@.@.@.@@@",
        "@.@@@.@@@@",
        ".@@@@@@@@.",
        "@.@.@@@.@.",
    )
    const val EXPECTED_PART_1_RESULT: Int = 13
    const val EXPECTED_PART_2_RESULT: Int = 43
}

fun main() {
    fun part1(input: List<String>): Int = transformGrid(input).sumOf { it.countX() }

    fun part2(input: List<String>, forceLog: Boolean = false): Int {
        var grid = input
        var result = 0
        do {
            val transformed = transformGrid(grid)
            log("$transformed", forceLog)
            val changes = transformed.sumOf { it.countX() }
            log("changes: $changes", forceLog)
            if (changes == 0) break
            result += changes
            grid = transformed.map { it.replace('x', '.') }
            log("$grid", forceLog)
        } while (true)

        return result
    }

    val testInput = Day04TestData.INPUT
    check(part1(testInput) == Day04TestData.EXPECTED_PART_1_RESULT)
    check(part2(testInput) == Day04TestData.EXPECTED_PART_2_RESULT)

    // Read the input from the `src/Day04.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}

private fun transformGrid(input: List<String>): List<String> {
    val len = input.first().length
    val list = buildList {
        add(emptyRow(len))
        addAll(input)
        add(emptyRow(len))
    }
    return list.windowed(3)
        .map { it.toTypedArray() }
        .map { getSolution(*it) }
}

fun getSolution(vararg row: String, forceLog: Boolean = false): String {
    require(row.size == 3) { "3 rows expected" }
    val len = row.first().length
    row.forEach { log(it + " [${it.length}]", forceLog) }
    log("", forceLog)
    require(row.all { it.length == len }) { "Equal rows expected" }

    val input = row.map { ".$it." }

    var result = ""
    val middleRow = row[1]
    middleRow.indices.take(len)
        .forEach { index ->
            if (middleRow[index] == '.') {
                log("skipping [$index]", forceLog)
                result += "."
            } else {
                val count = input
                    .map { it.drop(index).take(3) }
                    .onEach { log(it, forceLog) }
                    .sumOf { it.countRolls() }
                log("count: $count", forceLog)
                result += if (count <= 4) "x" else middleRow[index]
            }
            log("", forceLog)
        }
    log(result, forceLog)
    return result
}

fun emptyRow(count: Int) = ".".repeat(count)
private fun String.countX() = count { it == 'x' }
private fun String.countRolls() = count { it == '@' }

@Suppress("SimplifyBooleanWithConstants")
private fun log(s: String, forceLog: Boolean = false) =
    if (LOG_ALL || forceLog) println(s) else Unit
