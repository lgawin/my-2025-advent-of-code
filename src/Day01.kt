class Dial private constructor(startValue: Int) {

    constructor() : this(50)

    var arrow = startValue
    var zeroStops = 0
    var clicks = 0

    fun turn(input: String) {
        val sign = if (input.first() == 'R') 1 else -1
        val amount = input.substring(1).toInt()

        repeat(amount) {
            arrow += sign
            arrow = arrow.mod(100)
            if (arrow == 0) clicks++
        }
        if (arrow == 0) zeroStops++
    }
}

fun main() {
    fun part1(input: List<String>): Int = Dial()
        .apply { input.forEach(::turn) }
        .zeroStops

    fun part2(input: List<String>): Int = Dial()
        .apply { input.forEach(::turn) }
        .clicks

    // Test if implementation meets criteria from the description
    val testInput = listOf(
        "L68",
        "L30",
        "R48",
        "L5",
        "R60",
        "L55",
        "L1",
        "L99",
        "R14",
        "L82",
    )
    check(part1(testInput) == 3)
    check(part2(testInput) == 6)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
