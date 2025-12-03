// template for use when coding in Web Kotlin Playground - https://play.kotlinlang.org
// link to pre-filled playground: https://pl.kotl.in/kl0bf-zdx

const val LOG_ALL = false

fun main() {
    fun part1(input: List<String>) = input.fold(0L) { acc, input ->
        acc + solution1(input)
    }

    fun part2(input: List<String>) = input.fold(0L) { acc, input ->
        acc + solution2(input)
    }

    // -- working on implementations (step-by-step) on my own test data
    //check(solution1("", forceLog = true) == 0)
    //check(solution2("", forceLog = true) == 0L)

    // -- test data from task
    val testInput = listOf(
        "987654321111111",
        "811111111111119",
        "234234234234278",
        "818181911112111",
    )

    // -- check individual cases (part 1)
//     check(solution1("987654321111111") == 98)
// 	check(solution1("811111111111119") == 89)
// 	check(solution1("234234234234278") == 78)
// 	check(solution1("818181911112111") == 92)

    // -- check individual cases (part 2)
//     check(solution2("987654321111111") == 987654321111)
// 	check(solution2("811111111111119") == 811111111119)
// 	check(solution2("234234234234278") == 434234234278)
// 	check(solution2("818181911112111") == 888911112111)

    // -- check final calulations
//   	check(part1(testInput) == 357L)
//   	check(part2(testInput) == 3121910778619L)

    // -- run solution for myInput and print results
    printResult("Part1", myInput, ::part1)
    printResult("Part2", myInput, ::part2)
}

fun solution1(input: String, forceLog: Boolean = false): Int {
    TODO()
    // -- some possible checks
    //require(input.length)
    //return getSolution(input, forceLog).toInt()
}

fun solution2(input: String, forceLog: Boolean = false): Long {
    TODO()
    // -- some possible checks
    //require(input.length)
    //return getSolution(input, forceLog)
}

private fun getSolution(input: String, forceLog: Boolean = false): Long = 0L

@Suppress("SimplifyBooleanWithConstants")
fun log(s: String, forceLog: Boolean = false) =
    if (LOG_ALL || forceLog) println(s) else Unit

fun <T> printResult(header: String, input: List<String>, solution: (List<String>) -> T) {
    println("$header: ${if (input.isEmpty()) "N/A" else runCatching { solution(input) }.getOrDefault("N/A")}")
}

val myInput: List<String> = listOf(
    // -- paste data here
)
