import java.math.BigInteger

private const val LOG_ALL = false

object Day06TestData {
    val INPUT: List<String> = listOf(
        "123 328  51 64",
        " 45 64  387 23",
        "  6 98  215 314",
        "*   +   *   +",
    )
    val EXPECTED_PART_1_RESULT = BigInteger.valueOf(4277556L)
    val EXPECTED_PART_2_RESULT = BigInteger.valueOf(14L)
}

fun main() {
    fun part1(input: List<String>, forceLog: Boolean = false): BigInteger {
        val delimiter = """\s+""".toRegex()
        val operations = input.last().trim().split(delimiter)
        operations.forEach { log(it, forceLog) }

        val results = operations.map {
            when (it) {
                "+" -> BigInteger.ZERO
                "*" -> BigInteger.ONE
                else -> TODO("wtf")
            }
        }.toMutableList()
        input.dropLast(1).forEach { line ->
            val numbers = line.trim().split(delimiter)
                .map { BigInteger(it) }
                .withIndex()
            numbers.forEach { (index, number) ->
                val op = operations[index]
                when (op) {
                    "+" -> results[index] += number
                    "*" -> results[index] *= number
                    else -> TODO("wtf")
                }
            }
            log("" + results, forceLog)
        }
        return results.fold(BigInteger.ZERO) { acc, result -> acc + result }.also { log("" + it, forceLog) }
    }

    fun part2(input: List<String>): BigInteger = input.fold(BigInteger.ZERO) { acc, string ->
        acc + BigInteger.ZERO
    }

    val testInput = Day06TestData.INPUT
    check(part1(testInput) == Day06TestData.EXPECTED_PART_1_RESULT)
//    check(part2(testInput) == Day06TestData.EXPECTED_PART_2_RESULT)

    // Read the input from the `src/Day06.txt` file.
    val input = readInput("Day06")
    part1(input).println()
//    part2(input).println()
}

@Suppress("SimplifyBooleanWithConstants")
private fun log(s: String, forceLog: Boolean = false) = if (LOG_ALL || forceLog) println(s) else Unit