import java.math.BigInteger

private const val LOG_ALL = false

object Day06TestData {
    val INPUT: List<String> = listOf(
        "123 328  51 64 ",
        " 45 64  387 23 ",
        "  6 98  215 314",
        "*   +   *   +  ",
    )
    val EXPECTED_PART_1_RESULT = BigInteger.valueOf(4277556L)
    val EXPECTED_PART_2_RESULT = BigInteger.valueOf(3263827L)
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

    fun part2(input: List<String>, forceLog: Boolean = false): BigInteger {
        val calculator = Calculator().apply { init(input.last()) }
        val parser = NumbersParser(input.dropLast(1))

        val result = calculator.operations.asSequence().zip(parser.columns)
            .map { (operation, tokens) ->
                tokens.fold(if (operation == Calculator.Operation.ADDITION) BigInteger.ZERO else BigInteger.ONE) { acc, number ->
                    when (operation) {
                        Calculator.Operation.ADDITION -> acc + number.toBigInteger()
                        Calculator.Operation.MULTIPLICATION -> acc * number.toBigInteger()
                    }
                }.also { log("Interim result: $it", forceLog) }
            }
            .fold(BigInteger.ZERO) { a, b -> a + b }
            .also { log("Result: $it", forceLog) }
        return result
    }

    val testInput = Day06TestData.INPUT
    check(part1(testInput) == Day06TestData.EXPECTED_PART_1_RESULT)
    check(part2(testInput) == Day06TestData.EXPECTED_PART_2_RESULT)

    // Read the input from the `src/Day06.txt` file.
    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}

sealed interface Token {
    data object EndOfColumn : Token
    data object EndOfInput : Token

    @JvmInline
    value class Number(val value: Int) : Token
}

class NumbersParser(input: List<String>) {
    var column = 0
    private val lines = input.map { it.reversed() }

    init {
        log("Input $lines")
    }

    fun getNextToken(): Token = lines.mapNotNull { it.drop(column).firstOrNull() }
        .joinToString("")
        .also { column++ }
        .let {
            log("[$it]")
            when {
                it.isEmpty() -> Token.EndOfInput
                it.trim().isEmpty() -> Token.EndOfColumn
                else -> Token.Number(it.trim().toInt())
            }
        }

    val columns: Sequence<List<Int>>
        get() = sequence {
            var buffer = mutableListOf<Int>()
            while (true) {
                when (val token = getNextToken()) {
                    is Token.Number -> buffer.add(token.value)
                    Token.EndOfColumn -> {
                        yield(buffer.toList())
                        buffer = mutableListOf()
                    }

                    Token.EndOfInput -> {
                        yield(buffer.toList())
                        return@sequence
                    }
                }
            }
        }

}

class Calculator {
    enum class Operation {
        ADDITION, MULTIPLICATION,
    }

    var operations = emptyList<Operation>()
        private set
    var currentResults = emptyList<BigInteger>()
        private set

    fun init(initializationLine: String) {
        operations = initializationLine.trim().reversed().split("""\s+""".toRegex())
            .map { char ->
                when (char) {
                    "+" -> Operation.ADDITION
                    "*" -> Operation.MULTIPLICATION
                    else -> throw IllegalArgumentException("unexpected operation: [$char]")
                }
            }
        currentResults = operations.map {
            when (it) {
                Operation.ADDITION -> BigInteger.ZERO
                Operation.MULTIPLICATION -> BigInteger.ONE
            }
        }
    }
}

@Suppress("SimplifyBooleanWithConstants")
private fun log(s: String, forceLog: Boolean = false) = if (LOG_ALL || forceLog) println(s) else Unit