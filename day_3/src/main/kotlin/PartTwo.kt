import java.io.File
import java.nio.charset.StandardCharsets.UTF_8

class PartTwo(inputFile: File) {

    init {
        val grid = PartOne(inputFile).grid
        println("Part Two")
        inputFile.readLines(UTF_8).filter(String::isNotEmpty).forEach { line ->
            val split = line.split(Regex("\\s"))
            if (split.isNotFormattedProperly()) throw Exception("Input is ill-formatted")

            val id = split[0]
            val (leftMargin, rightMargin) = split[2]
                .dropLast(1)
                .split(',')
                .map(String::toInt)
            val (width, height) = split[3].split('x').map(String::toInt)

            var currentIndex1 = leftMargin
            var isOverlapping = false
            while (currentIndex1 < leftMargin + width) {
                var currentIndex2 = rightMargin
                while (currentIndex2 < rightMargin + height) {
                    if (grid[currentIndex1][currentIndex2] > 1)
                        isOverlapping = true
                    currentIndex2 += 1
                }
                currentIndex1 += 1
            }
            if (!isOverlapping) {
                println("Found non-overlapping grid $id")
                return@forEach
            }
        }
    }

    private fun <E> List<E>.isNotFormattedProperly() = size != 4
}
