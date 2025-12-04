import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day04Test {
    val testInput = Day04TestData.INPUT

    @Test
    fun `row 1 test`() {
        assertEquals("..xx.xx@x.", getSolution(emptyRow(10), testInput[0], testInput[1]))
    }

    @Test
    fun `row 2 test`() {
        assertEquals("x@@.@.@.@@", getSolution(testInput[0], testInput[1], testInput[2]))
    }

    @Test
    fun `row 3 test`() {
        assertEquals("@@@@@.x.@@", getSolution(testInput[1], testInput[2], testInput[3]))
    }

    @Test
    fun `row 4 test`() {
        assertEquals("@.@@@@..@.", getSolution(testInput[2], testInput[3], testInput[4]))
    }

    @Test
    fun `row 5 test`() {
        assertEquals("x@.@@@@.@x", getSolution(testInput[3], testInput[4], testInput[5]))
    }

    @Test
    fun `row 6 test`() {
        assertEquals(".@@@@@@@.@", getSolution(testInput[4], testInput[5], testInput[6]))
    }

    @Test
    fun `row 7 test`() {
        assertEquals(".@.@.@.@@@", getSolution(testInput[5], testInput[6], testInput[7]))
    }

    @Test
    fun `row 8 test`() {
        assertEquals("x.@@@.@@@@", getSolution(testInput[6], testInput[7], testInput[8]))
    }

    @Test
    fun `row 9 test`() {
        assertEquals(".@@@@@@@@.", getSolution(testInput[7], testInput[8], testInput[9]))
    }

    @Test
    fun `row 10 test`() {
        assertEquals("x.x.@@@.x.", getSolution(testInput[8], testInput[9], emptyRow(10)))
    }
}