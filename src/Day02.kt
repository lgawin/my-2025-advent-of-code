import java.math.BigInteger
import kotlin.collections.buildSet
import kotlin.ranges.step

fun main() {

    fun part1(input: List<String>): BigInteger = input.fold(BigInteger.ZERO) { acc, string ->
        acc + checkRange(string)
    }

    fun part2(input: List<String>): BigInteger = input.fold(BigInteger.ZERO) { acc, string ->
        acc + checkRange2(string)
    }
    // Test if implementation meets criteria from the description
    val testInput = listOf(
        "11-22",
        "95-115",
        "998-1012",
        "1188511880-1188511890",
        "222220-222224",
        "1698522-1698528",
        "446443-446449",
        "38593856-38593862",
        "565653-565659",
        "824824821-824824827",
        "2121212118-2121212124",
    )
    check(part1(testInput) == 1227775554.toBigInteger())
    check(part2(testInput) == 4174379265.toBigInteger())

    // Read the input from the `src/Day02.txt` file.
    val input = readInput("Day02").single().split(",")
    part1(input).println()
    part2(input).println()
}

fun checkRange(input: String): BigInteger {
    var (first, second) = input.split("-")
    log("$first -  $second")
    if (first.length % 2 == 1) {
        val numberOfZeros = (first.length / 2).coerceAtLeast(0)
        first = ("1" + "0".repeat(numberOfZeros)).repeat(2)
    }
    if (second.length % 2 == 1) {
        second = "9".repeat(second.length - 1)
    }
    if (first > second) return BigInteger.ZERO
    log(" $first -  $second")
    if (first.length != second.length) {
        if (first.length > second.length) return BigInteger.ZERO
        var result = BigInteger.ZERO
        var a = first
        (first.length..second.length step 2).forEach {
            val b = "9".repeat(it).toBigInteger().coerceAtMost(second.toBigInteger())
            log("$a --> $b")
            result += checkRange("$a-$b")
            a = ("1" + "0".repeat(it / 2)).repeat(2)
        }
        return result
    } else {
        val t = ((first.length - 1) / 2).coerceAtLeast(0)
        val half = "1" + "0".repeat(t)
        val step = (half + "1").toBigInteger()
        var start = (half + half).toBigInteger()
        while (start < first.toBigInteger()) {
            start += step
        }
        return checkRange(start, second.toBigInteger(), step)
    }
}

fun checkRange(firstId: BigInteger, lastId: BigInteger, step: BigInteger): BigInteger {
    var result = BigInteger.ZERO
    var first = firstId
    while (first <= lastId) {
        result += first
        first += step
    }
    return result
}

fun log(s: String) = Unit

fun checkRange2(input: String): BigInteger {
    val (first, second) = input.split("-")
    return checkRange2(first, second).sumOf { it }
}

private fun checkRange2(first: String, second: String): Set<BigInteger> {
    require(first.toBigInteger() <= second.toBigInteger()) { "invalid start $first and end $second" }
    return if (first.length != second.length) {
        if (first.length > second.length) return emptySet()
        buildSet {
            var a = first
            (first.length..second.length).forEach {
                val b = "9".repeat(it).toBigInteger().coerceAtMost(second.toBigInteger())
                log("$a --> $b")
                addAll(checkRange2(a, b.toString()))
                a = ("1" + "0".repeat(it))
            }
        }
    } else {
        buildSet {
            (first.length / 2).downTo(1).forEach { len ->
                if (first.length > len && first.length % len == 0) {
                    addAll(
                        checkRange2(
                            first.toBigInteger(),
                            second.toBigInteger(),
                            factor1 = len,
                            factor2 = first.length / len
                        )
                    )
                }
            }
        }
    }
}

private fun checkRange2(start: BigInteger, end: BigInteger, factor1: Int, factor2: Int): Set<BigInteger> {
    log("$start - $end / by $factor1")
    val pattern = "1" + "0".repeat(factor1 - 1)
    val seed = pattern.repeat(factor2)
    val step = (("1" + "0".repeat(factor1 - 1)).repeat(factor2 - 1) + "1").toBigInteger()
    log("$seed / $step")

    var x = seed.toBigInteger()
    while (x < start) {
        x += step
    }
    return buildSet {
        while (x <= end) {
            log(" acc: $x")
            add(x)
            x += step
        }
    }
}
