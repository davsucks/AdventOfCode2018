import java.io.File
import java.nio.charset.StandardCharsets.UTF_8

class PartOne(inputFile: File) {
    private val grid = Array(10000) { IntArray(10000) { 0 } }
    private var overlappingCellCount = 0

    init {
        inputFile.readLines(UTF_8).filter(String::isNotEmpty).forEach { line ->
            val split = line.split(Regex("\\s"))
            if (split.isNotFormattedProperly()) throw Exception("Input is ill-formatted")

            val (leftMargin, rightMargin) = split[2].dropLast(1).split(',').map(String::toInt)
            println("Got $leftMargin,$rightMargin")
            val (width, height) = split[3].split('x').map(String::toInt)
            println("Got ${width}x$height")

            var currentIndex1 = leftMargin
            while (currentIndex1 < leftMargin + width) {
                var currentIndex2 = rightMargin
                while (currentIndex2 < rightMargin + height) {
                    grid[currentIndex1][currentIndex2] += 1
                    if (grid[currentIndex1][currentIndex2] == 2) overlappingCellCount += 1
                    currentIndex2 += 1
                }
                currentIndex1 += 1
            }
        }
        println("Overlapping cell count == $overlappingCellCount")
    }

    private fun <E> List<E>.isNotFormattedProperly() = size != 4
}
