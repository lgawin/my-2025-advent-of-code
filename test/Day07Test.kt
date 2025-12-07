import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day07Test {
    private val input = Day07TestData.INPUT

    @Test
    fun part1() {
        val result = input.runningFold(TachyonMinifoldState()) { acc, string ->
            acc.applyInput(string)
        }
        val expectedVisualization = """
            .......S.......
            .......|.......
            ......|^|......
            ......|.|......
            .....|^|^|.....
            .....|.|.|.....
            ....|^|^|^|....
            ....|.|.|.|....
            ...|^|^|||^|...
            ...|.|.|||.|...
            ..|^|^|||^|^|..
            ..|.|.|||.|.|..
            .|^|||^||.||^|.
            .|.|||.||.||.|.
            |^|^|^|^|^|||^|
            |.|.|.|.|.|||.|
        """.trimIndent().lines()
        val visualization = input.zip(result) { string, state -> visualize(string, state.beams) }
        visualization.forEachIndexed { index, line ->
            assertEquals(expectedVisualization[index], line)
        }
        assertEquals(21, result.last().splits)
    }
}

private fun visualize(string: String, beams: Set<Int>): String {
    var result = string
    beams.forEach { index ->
        result = when (val character = result[index]) {
            '.' -> result.replaceRange(index, index + 1, "|")
            '^' -> {
                if (beams.contains(index))
                    result.replaceRange(index - 1, index + 2, "|^|")
                else result
            }

            '|' -> result
            'S' -> result
            else -> error("wtf [$character]")
        }
    }
    return result
}
