import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day01Test {

    @Nested
    inner class Part1 {

        @Test
        fun `turn right`() {
            val dial = Dial()
            dial.turn("R1")
            assertEquals(51, dial.arrow)
        }

        @Test
        fun `turn left`() {
            val dial = Dial()
            dial.turn("L2")
            assertEquals(48, dial.arrow)
        }

        @Test
        fun `turn right with overflow`() {
            val dial = Dial()
            dial.turn("R51")
            assertEquals(1, dial.arrow)
        }

        @Test
        fun `turn left with overflow`() {
            val dial = Dial()
            dial.turn("L72")
            assertEquals(78, dial.arrow)
        }

        @Test
        fun `turn right with multi-overflow`() {
            val dial = Dial()
            dial.turn("R463")
            assertEquals(13, dial.arrow)
        }

        @Test
        fun `turn left with multi-overflow`() {
            val dial = Dial()
            dial.turn("L389")
            assertEquals(61, dial.arrow)
        }
    }

    @Nested
    inner class Part2 {

        @Test
        fun `starts with 0 clicks`() {
            val dial = Dial()
            assertEquals(0, dial.clicks)
        }

        @Test
        fun `records click when reaching 0 while turning left`() {
            val dial = Dial()
            dial.turn("L50")
            assertEquals(1, dial.clicks)
            dial.turn("L100")
            assertEquals(2, dial.clicks)
            dial.turn("L300")
            assertEquals(5, dial.clicks)
        }

        @Test
        fun `records click when passing 0 while turning left`() {
            val dial = Dial()
            dial.turn("L49")
            assertEquals(0, dial.clicks)
            dial.turn("L2")
            assertEquals(1, dial.clicks)
            assertEquals(99, dial.arrow)
        }

        @Test
        fun `records click when reaching 0 while turning right`() {
            val dial = Dial()
            dial.turn("R50")
            assertEquals(1, dial.clicks)
            dial.turn("R100")
            assertEquals(2, dial.clicks)
            dial.turn("R900")
            assertEquals(11, dial.clicks)
        }

        @Test
        fun `records click when passing 0 while turning right`() {
            val dial = Dial()
            dial.turn("R49")
            assertEquals(0, dial.clicks)
            dial.turn("R8")
            assertEquals(1, dial.clicks)
            assertEquals(7, dial.arrow)
        }

        @Test
        fun `records multiple clicks when passing 0 while turning left`() {
            val dial = Dial()
            dial.turn("L451")
            assertEquals(5, dial.clicks)
        }

        @Test
        fun `records multiple clicks when passing 0 while turning right`() {
            val dial = Dial()
            dial.turn("R1000")
            assertEquals(10, dial.clicks)
        }
    }
}