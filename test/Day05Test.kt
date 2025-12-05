import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day05Test {

    @Test
    fun `test 1`() {
        val list = listOf(1L to 3L, 10L to 20L, 30L to 40L)
        val pair = 2L to 5L
        val index = list.binarySearch(pair, ingredientsRangesComparator)
        assertEquals(0, index)
    }

    @Test
    fun `test 2`() {
        val list = listOf(1L to 3L, 10L to 20L, 30L to 40L)
        val pair = 7L to 12L
        val index = list.binarySearch(pair, ingredientsRangesComparator)
        assertEquals(1, index)
    }

    @Test
    fun `test 3`() {
        val list = listOf(1L to 3L, 10L to 20L, 30L to 40L)
        val pair = 12L to 25L
        val index = list.binarySearch(pair, ingredientsRangesComparator)
        assertEquals(1, index)
    }

    @Test
    fun `test 4`() {
        val list = listOf(1L to 3L, 10L to 20L, 30L to 40L)
        val pair = 39L to 100L
        val index = list.binarySearch(pair, ingredientsRangesComparator)
        assertEquals(2, index)
    }

    @Test
    fun `test 5`() {
        val list = listOf(10L to 20L, 30L to 40L)
        val pair = 1L to 10L
        val index = list.binarySearch(pair, ingredientsRangesComparator)
        assertEquals(0, index)
    }

    @Test
    fun `test 6`() {
        val list = listOf(10L to 20L, 30L to 40L)
        val pair = 1L to 9L
        val index = list.binarySearch(pair, ingredientsRangesComparator)
        assertEquals(-1, index)
    }

    @Test
    fun `test 7`() {
        val list = listOf(10L to 20L, 30L to 40L)
        val pair = 40L to 42L
        val index = list.binarySearch(pair, ingredientsRangesComparator)
        assertEquals(1, index)
    }

    @Test
    fun `test 8`() {
        val list = listOf(10L to 20L, 30L to 40L)
        val pair = 41L to 42L
        val index = list.binarySearch(pair, ingredientsRangesComparator)
        assertEquals(-3, index)
    }

    @Test
    fun `test 9`() {
        val list = listOf(10L to 20L, 30L to 40L)
        val pair = 21L to 29L
        val index = list.binarySearch(pair, ingredientsRangesComparator)
        assertEquals(-2, index)
    }

    @Test
    fun simpleAdd1() {
        val list = mutableListOf(10L to 20L)
        list.addRange("1-3")
        assertEquals(listOf(1L to 3L, 10L to 20L), list)
    }

    @Test
    fun simpleAdd2() {
        val list = mutableListOf(10L to 20L)
        list.addRange("22-23")
        assertEquals(listOf(10L to 20L, 22L to 23L), list)
    }

    @Test
    fun mergingAdd1_1() {
        val list = mutableListOf(10L to 20L)
        list.addRange("19-23")
        assertEquals(listOf(10L to 23L), list)
    }

    @Test
    fun mergingAdd1_2() {
        val list = mutableListOf(10L to 20L)
        list.addRange("20-23")
        assertEquals(listOf(10L to 23L), list)
    }

    @Test
    fun mergingAdd1_3() {
        val list = mutableListOf(10L to 20L)
        list.addRange("21-23")
        assertEquals(listOf(10L to 23L), list)
    }

    @Test
    fun mergingAdd2_1() {
        val list = mutableListOf(10L to 20L)
        list.addRange("8-11")
        assertEquals(listOf(8L to 20L), list)
    }

    @Test
    fun mergingAdd2_2() {
        val list = mutableListOf(10L to 20L)
        list.addRange("8-10")
        assertEquals(listOf(8L to 20L), list)
    }

    @Test
    fun mergingAdd2_3() {
        val list = mutableListOf(10L to 20L)
        list.addRange("8-9")
        assertEquals(listOf(8L to 20L), list)
    }

    @Test
    fun mergingAdd3_1() {
        val list = mutableListOf(10L to 20L)
        list.addRange("7-22")
        assertEquals(listOf(7L to 22L), list)
    }

    @Test
    fun mergingAdd3_2() {
        val list = mutableListOf(10L to 20L)
        list.addRange("11-29")
        assertEquals(listOf(10L to 29L), list)
    }

    @Test
    fun mergingAdd4_1() {
        val list = mutableListOf(10L to 20L, 30L to 40L)
        list.addRange("21-29")
        assertEquals(listOf(10L to 40L), list)
    }

    @Test
    fun mergingAdd4_2() {
        val list = mutableListOf(10L to 20L, 30L to 40L)
        list.addRange("1-50")
        assertEquals(listOf(1L to 50L), list)
    }

    @Test
    fun mergingAdd4_3() {
        val list = mutableListOf(3L to 5L, 7L to 10L, 12L to 23L, 30L to 40L, 60L to 70L)
        list.addRange("1-50")
        assertEquals(listOf(1L to 50L, 60L to 70L), list)
    }

    @Test
    fun mergingAdd4_4() {
        val list = mutableListOf(3L to 5L, 7L to 10L, 12L to 23L, 30L to 40L, 51L to 70L)
        list.addRange("1-50")
        assertEquals(listOf(1L to 70L), list)
    }

    @Test
    fun xx() {
        val list = mutableListOf(1L to 3L)
        list.addRange("2-5")
        assertEquals(listOf(1L to 5L), list)
    }

    @Test
    fun yy() {
        val list = listOf(
            "3-5",
            "10-14",
            "16-20",
        ).map { it.split("-").let { it[0].toLong() to it[1].toLong()}}.toMutableList()
        list.addRange("12-18")
        assertEquals(listOf(3L to 5L, 10L to 20L), list)
    }
}