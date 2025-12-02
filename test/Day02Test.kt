import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.math.BigInteger
import kotlin.math.log10
import kotlin.random.Random
import kotlin.test.assertEquals

class Day02Test {

    @Nested
    inner class Part1 {

        @Test
        fun `test 01`() {
            // 11, 22
            assertEquals(33.toBigInteger(), checkRange("11-22"))
        }

        @Test
        fun `test 02`() {
            // 99
            assertEquals(99.toBigInteger(), checkRange("95-115"))
            assertEquals(99.toBigInteger(), checkRange("95-99"))
        }

        @Test
        fun `test 03`() {
            // 1010
            assertEquals(1010.toBigInteger(), checkRange("998-1012"))
            assertEquals(1010.toBigInteger(), checkRange("1000-1012"))
        }

        @Test
        fun `test 04`() {
            // 1188511885
            assertEquals(1188511885.toBigInteger(), checkRange("1188511880-1188511890"))
        }

        @Test
        fun `test 05`() {
            // 222222
            assertEquals(222222.toBigInteger(), checkRange("222220-222224"))
        }

        @Test
        fun `test 06`() {
            // none
            assertEquals(0.toBigInteger(), checkRange("1698522-1698528"))
        }

        @Test
        fun `test 07`() {
            // 446446
            assertEquals(446446.toBigInteger(), checkRange("446443-446449"))
        }

        @Test
        fun `test 08`() {
            // 38593859
            assertEquals(38593859.toBigInteger(), checkRange("38593856-38593862"))
        }

        @Test
        fun `test 09`() {
            // none
            assertEquals(0.toBigInteger(), checkRange("565653-565659"))
        }

        @Test
        fun `test 10`() {
            // none
            assertEquals(0.toBigInteger(), checkRange("824824821-824824827"))
        }

        @Test
        fun `test 11`() {
            // none
            assertEquals(0.toBigInteger(), checkRange("2121212118-2121212124"))
        }

        @Test
        fun `test 12`() {
            val a = checkRange("10-99")
            val b = checkRange("1000-10000")
            val c = checkRange("100000-1000000")
            val d = checkRange("10000000-100000000")
            val e = checkRange("1000000000-10000000000")
            val f = checkRange("100000000000-1000000000000")
            assertEquals(495.toBigInteger(), a)
            assertEquals(495405.toBigInteger(), b)
            assertEquals((495 + 495405).toBigInteger(), checkRange("10-10000"))
            assertEquals((a + b + c + d + e + f), checkRange("1-1000000000000"))
        }
    }

    @Nested
    inner class Part2 {

        @Test
        fun `test 01`() {
            // 11, 22
            assertEquals(33.toBigInteger(), checkRange2("11-22"))
        }

        @Test
        fun `test 02`() {
            // 99, 111
            assertEquals(210.toBigInteger(), checkRange2("95-115"))
            assertEquals(210.toBigInteger(), checkRange2("99-111"))
        }

        @Test
        fun `test 03`() {
            // 999, 1010
            assertEquals(2009.toBigInteger(), checkRange2("998-1012"))
            assertEquals(2009.toBigInteger(), checkRange2("999-1010"))
        }

        @Test
        fun `test 04`() {
            // 1188511885
            assertEquals(1188511885.toBigInteger(), checkRange2("1188511880-1188511890"))
        }

        @Test
        fun `test 05`() {
            // 222222
            assertEquals(222222.toBigInteger(), checkRange2("222220-222224"))
        }

        @Test
        fun `test 06`() {
            // none
            assertEquals(0.toBigInteger(), checkRange2("1698522-1698528"))
        }

        @Test
        fun `test 07`() {
            // 446446
            assertEquals(446446.toBigInteger(), checkRange2("446443-446449"))
        }

        @Test
        fun `test 08`() {
            // 38593859
            assertEquals(38593859.toBigInteger(), checkRange2("38593856-38593862"))
        }

        @Test
        fun `test 09`() {
            // 565656
            assertEquals(565656.toBigInteger(), checkRange2("565653-565659"))
        }

        @Test
        fun `test 10`() {
            // 824824824
            assertEquals(824824824.toBigInteger(), checkRange2("824824821-824824827"))
        }

        @Test
        fun `test 11`() {
            // 2121212121
            assertEquals(2121212121.toBigInteger(), checkRange2("2121212118-2121212124"))
        }

        @Test
        fun `test 12`() {
            val a = checkRange2("1-100")
            val b = checkRange2("101-1000")
            val c = checkRange2("1001-10000")
            val d = checkRange2("1${"0".repeat(4)}-${"9".repeat(8)}")
            val e = checkRange2("1${"0".repeat(8)}-${"9".repeat(9)}")
            val f = checkRange2("1${"0".repeat(9)}-${"9".repeat(10)}")
            assertEquals(495.toBigInteger(), a)
            assertEquals(4995.toBigInteger(), b)
            assertEquals(495405.toBigInteger(), c)
            assertEquals(a + b + c, checkRange2("1-10000"))
            assertEquals(495594585450.toBigInteger(), d)
            assertEquals(495045044550.toBigInteger(), e)
            assertEquals(495445904500410.toBigInteger(), f)
            assertEquals(a + b + c + d + e + f, checkRange2("1-${"9".repeat(10)}"))
        }
    }
}
