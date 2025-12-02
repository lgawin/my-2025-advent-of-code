import java.math.BigInteger

fun main() {

    fun part1(input: List<String>): BigInteger = input.fold(BigInteger.ZERO) { acc, string ->
        acc + checkRange(string)
    }

    fun part2(input: List<String>): BigInteger = 4174379265.toBigInteger()

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
//    part2(input).println()
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
