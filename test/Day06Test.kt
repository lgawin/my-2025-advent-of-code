import org.junit.jupiter.api.Test
import java.math.BigInteger
import kotlin.test.assertEquals

class Day06Test {

    val input = Day06TestData.INPUT

    @Test
    fun initialization() {
        val calculator = Calculator().apply { init(input.last()) }
        assertEquals(
            listOf(
                Calculator.Operation.ADDITION,
                Calculator.Operation.MULTIPLICATION,
                Calculator.Operation.ADDITION,
                Calculator.Operation.MULTIPLICATION,
            ),
            calculator.operations,
        )
        assertEquals(
            listOf(
                BigInteger.ZERO,
                BigInteger.ONE,
                BigInteger.ZERO,
                BigInteger.ONE,
            ),
            calculator.currentResults,
        )
    }

    @Test
    fun `parse numbers`() {
        val parser = NumbersParser(input.dropLast(1))
        assertEquals(Token.Number(4), parser.getNextToken())
        assertEquals(Token.Number(431), parser.getNextToken())
        assertEquals(Token.Number(623), parser.getNextToken())
        assertEquals(Token.EndOfColumn, parser.getNextToken())
        assertEquals(Token.Number(175), parser.getNextToken())
        assertEquals(Token.Number(581), parser.getNextToken())
        assertEquals(Token.Number(32), parser.getNextToken())
        assertEquals(Token.EndOfColumn, parser.getNextToken())
        assertEquals(Token.Number(8), parser.getNextToken())
        assertEquals(Token.Number(248), parser.getNextToken())
        assertEquals(Token.Number(369), parser.getNextToken())
        assertEquals(Token.EndOfColumn, parser.getNextToken())
        assertEquals(Token.Number(356), parser.getNextToken())
        assertEquals(Token.Number(24), parser.getNextToken())
        assertEquals(Token.Number(1), parser.getNextToken())
        assertEquals(Token.EndOfInput, parser.getNextToken())
    }

    @Test
    fun `parse columns`() {
        val parser = NumbersParser(input.dropLast(1))
        assertEquals(
            listOf(
                listOf(4, 431, 623),
                listOf(175, 581, 32),
                listOf(8, 248, 369),
                listOf(356, 24, 1),
            ),
            parser.columns.toList(),
        )
    }

    @Test
    fun calculate() {
        val calculator = Calculator().apply { init(input.last()) }
        val parser = NumbersParser(input.dropLast(1))

        val result = calculator.operations.asSequence().zip(parser.columns)
            .map { (operation, tokens) ->
                tokens.fold(if (operation == Calculator.Operation.ADDITION) BigInteger.ZERO else BigInteger.ONE) { acc, number ->
                    when (operation) {
                        Calculator.Operation.ADDITION -> acc + number.toBigInteger()
                        Calculator.Operation.MULTIPLICATION -> acc * number.toBigInteger()
                    }
                }.also(::println)
            }
            .fold(BigInteger.ZERO) { a, b -> a + b }

        assertEquals(3263827.toBigInteger(), result)
    }
}
