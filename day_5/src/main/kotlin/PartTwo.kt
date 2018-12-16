import java.io.File
import PolymerReducer.capitalize

class PartTwo(input: File) {
    init {
        val polymer = input.readLines().first().trim()
        val reductionList = listOf('a'..'z').flatten().map { char ->
            val capitalization = char.capitalize()
            val reduction = PolymerReducer.reduce(polymer.filterNot { it == char || it == capitalization })
            println("Filtering $capitalization/$char results in a length of ${reduction.length}")
            reduction
        }
        println("Shortest reduction was ${reductionList.sortedBy { it.length }.first().length}")
    }
}
