fun main() {
    fun part1(input: List<String>) = input.fold(0L) { acc, bank -> acc + getJoltage1(bank) }
    fun part2(input: List<String>) = input.fold(0L) { acc, bank -> acc + getJoltage2(bank) }

    val testInput = listOf(
        "987654321111111",
        "811111111111119",
        "234234234234278",
        "818181911112111",
    )

    check(part1(testInput) == 357L)
    check(part2(testInput) == 3121910778619L)

    // Read the input from the `src/Day03.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}

fun getJoltage1(bank: String, forceLog: Boolean = false): Int = getJoltage(bank, count = 2, forceLog).toInt()

fun getJoltage2(bank: String, forceLog: Boolean = false): Long = getJoltage(bank, count = 12, forceLog)

fun getJoltage(bank: String, count: Int, forceLog: Boolean = false): Long {
    var result = ""

    val indexed = bank.withIndex()
    var start = IndexedValue(-1, '0')
    var end = indexed.last()

    (1 until count).reversed().forEach { toRemain ->
        log("toRemain: $toRemain", forceLog)
        with(indexed.drop(start.index + 1)) {
            start = first()
            getMaxBattery(drop(1).dropLast(toRemain), start.value, forceLog = forceLog)?.let {
                start = it
            }
        }
        log("start: [${start.index}] = ${start.value}", forceLog)
        result += start.value
    }

    getMaxBattery(indexed.reversed().drop(1).dropLast(start.index + 1), end.value, forceLog = forceLog)?.let {
        end = it
    }
    log("end: [${end.index}] = ${end.value}", forceLog)
    result += end.value

    log("result: $result", forceLog)
    return result.toLong()
}

private fun getMaxBattery(
    bank: List<IndexedValue<Char>>,
    watermark: Char,
    forceLog: Boolean = false
): IndexedValue<Char>? {
    log("bank: ${bank.map { it.value }.joinToString("")} [${bank.size}], watermark: $watermark", forceLog)
    var ret: IndexedValue<Char>? = null
    bank.forEach {
        val value = it.value
        val currentWatermark = (ret?.value ?: watermark)
        if (value > currentWatermark) {
            log("-- $currentWatermark -- vs $value -> [${it.index}] $value", forceLog)
            ret = it
        }
    }
    return ret
}

private fun log(s: String, forceLog: Boolean = false) =
    if (forceLog) println(s) else Unit
// 	println(s)
