import java.math.BigInteger

object Day06TestData {
    val INPUT: List<String> = listOf(
        "123 328  51 64 ",
        " 45 64  387 23 ",
        "  6 98  215 314",
        "*   +   *   +  ",
    )
    val EXPECTED_PART_1_RESULT: BigInteger = BigInteger.valueOf(4277556L)
    val EXPECTED_PART_2_RESULT: BigInteger = BigInteger.valueOf(3263827L)
}

fun main() {
    fun part1(input: List<String>): BigInteger =
        with(Calculator(mode = Calculator.Mode.MODE_1)) {
            setInput(input)
            return results.fold(BigInteger.ZERO) { acc, value -> acc + value }
        }

    fun part2(input: List<String>): BigInteger =
        with(Calculator(mode = Calculator.Mode.MODE_2)) {
            setInput(input)
            return results.fold(BigInteger.ZERO) { acc, value -> acc + value }
        }


    val testInput = Day06TestData.INPUT
    check(part1(testInput) == Day06TestData.EXPECTED_PART_1_RESULT)
    check(part2(testInput) == Day06TestData.EXPECTED_PART_2_RESULT)

    // Read the input from the `src/Day06.txt` file.
    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}

interface NumbersParser {
    val columns: Sequence<List<Int>>
}

class Mode1NumbersParser(input: List<String>) : NumbersParser {
    private val lines = input

    init {
        log("Input $lines")
    }

    override val columns: Sequence<List<Int>>
        get() {
            return sequence {
                val delimiter = """\s+""".toRegex()
                val parsedRows = lines.map { line ->
                    line.trim().split(delimiter).map { it.toInt() }
                }
                (0..parsedRows.size).forEach { index ->
                    yield(parsedRows.map { it[index] })
                }
            }
        }
}

class Mode2NumbersParser(input: List<String>) : NumbersParser {
    var column = 0
    private val lines = input.map { it.reversed() }

    init {
        log("Input $lines")
    }

    sealed interface Token {
        data object EndOfColumn : Token
        data object EndOfInput : Token

        @JvmInline
        value class Number(val value: Int) : Token
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

    override val columns: Sequence<List<Int>>
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

class Calculator(val mode: Mode) {
    enum class Mode { MODE_1, MODE_2 }
    enum class Operation { ADDITION, MULTIPLICATION, }

    var operations = emptyList<Operation>()
        private set
    lateinit var parser: NumbersParser
        private set

    fun setInput(input: List<String>) {
        val operationsLine = input.last().trim()
        init(
            when (mode) {
                Mode.MODE_1 -> operationsLine
                Mode.MODE_2 -> operationsLine.reversed()
            }
        )
        val parserInput = input.dropLast(1)
        parser = when (mode) {
            Mode.MODE_1 -> Mode1NumbersParser(parserInput)
            Mode.MODE_2 -> Mode2NumbersParser(parserInput)
        }
    }

    private fun init(initializationLine: String) {
        operations = initializationLine.split("""\s+""".toRegex())
            .map { char ->
                when (char) {
                    "+" -> Operation.ADDITION
                    "*" -> Operation.MULTIPLICATION
                    else -> throw IllegalArgumentException("unexpected operation: [$char]")
                }
            }
    }

    val results: Sequence<BigInteger>
        get() = parser.columns.mapIndexed { index, ints ->
            val op = operations[index]
            val initialValue = when (op) {
                Operation.ADDITION -> BigInteger.ZERO
                Operation.MULTIPLICATION -> BigInteger.ONE
            }
            ints.fold(initialValue) { acc, number ->
                when (op) {
                    Operation.ADDITION -> acc + number.toBigInteger()
                    Operation.MULTIPLICATION -> acc * number.toBigInteger()
                }
            }
        }
}
