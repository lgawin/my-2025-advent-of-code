import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day03Test {

    @Test
    fun `dev test 1`() {
        assertEquals(99, getJoltage1("3651321425683967935"))
    }

    @Test
    fun `dev test 2`() {
        assertEquals(99L, getJoltage("3651321425683967935", count = 2))
    }

    @Test
    fun `dev test 3`() {
        assertEquals(98L, getJoltage("987654321111111", count = 2))
    }

    @Test
    fun `dev test 4`() {
        assertEquals(995L, getJoltage("3651321425683967935", count = 3))
    }

    @Test
    fun `dev test 5`() {
        assertEquals(9935L, getJoltage("3651321425683967935", count = 4))
    }

    @Test
    fun `dev test 6`() {
        assertEquals(97935L, getJoltage("3651321425683967935", count = 5))
    }

    @Nested
    inner class Part1 {

        @Test
        fun `test 1 from task`() {
            assertEquals(98, getJoltage1("987654321111111"))
        }

        @Test
        fun `test 2 from task`() {
            assertEquals(89, getJoltage1("811111111111119"))
        }

        @Test
        fun `test 3 from task`() {
            assertEquals(78, getJoltage1("234234234234278"))
        }

        @Test
        fun `test 4 from task`() {
            assertEquals(92, getJoltage1("818181911112111"))
        }
    }

    @Nested
    inner class Part2 {

        @Test
        fun `test 1 from task`() {
            assertEquals(987654321111, getJoltage2("987654321111111"))
        }

        @Test
        fun `test 2 from task`() {
            assertEquals(811111111119, getJoltage2("811111111111119"))
        }

        @Test
        fun `test 3 from task`() {
            assertEquals(434234234278, getJoltage2("234234234234278"))
        }

        @Test
        fun `test 4 from task`() {
            assertEquals(888911112111, getJoltage2("818181911112111"))
        }
    }
}