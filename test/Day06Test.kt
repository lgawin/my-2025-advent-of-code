import Mode2NumbersParser.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.math.BigInteger
import kotlin.test.assertEquals

class Day06Test {

    val input = Day06TestData.INPUT

    @Nested
    inner class Part1 {
        private val calculator = Calculator(mode = Calculator.Mode.MODE_1).apply { setInput(input) }

        @Test
        fun initialization() {
            assertEquals(
                listOf(
                    Calculator.Operation.MULTIPLICATION,
                    Calculator.Operation.ADDITION,
                    Calculator.Operation.MULTIPLICATION,
                    Calculator.Operation.ADDITION,
                ),
                calculator.operations,
            )
        }

        @Test
        fun `parse columns`() {
            assertEquals(
                listOf(
                    listOf(123, 45, 6),
                    listOf(328, 64, 98),
                    listOf(51, 387, 215),
                    listOf(64, 23, 314),
                ),
                calculator.parser.columns.toList(),
            )
        }

        @Test
        fun `get results`() {
            assertEquals(
                listOf(
                    33210.toBigInteger(),
                    490.toBigInteger(),
                    4243455.toBigInteger(),
                    401.toBigInteger(),
                ),
                calculator.results.toList(),
            )
        }
    }

    @Nested
    inner class Part2 {
        private val calculator = Calculator(mode = Calculator.Mode.MODE_2).apply { setInput(input) }

        @Test
        fun initialization() {
            assertEquals(
                listOf(
                    Calculator.Operation.ADDITION,
                    Calculator.Operation.MULTIPLICATION,
                    Calculator.Operation.ADDITION,
                    Calculator.Operation.MULTIPLICATION,
                ),
                calculator.operations,
            )
        }

        @Test
        fun `parse numbers`() {
            val parser = calculator.parser as Mode2NumbersParser
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
            val calculator = Calculator(mode = Calculator.Mode.MODE_2).apply { setInput(input) }
            assertEquals(
                listOf(
                    listOf(4, 431, 623),
                    listOf(175, 581, 32),
                    listOf(8, 248, 369),
                    listOf(356, 24, 1),
                ),
                calculator.parser.columns.toList(),
            )
        }

        @Test
        fun `get results`() {
            assertEquals(
                listOf(
                    1058.toBigInteger(),
                    3253600.toBigInteger(),
                    625.toBigInteger(),
                    8544.toBigInteger(),
                ),
                calculator.results.toList(),
            )
        }
    }
}
