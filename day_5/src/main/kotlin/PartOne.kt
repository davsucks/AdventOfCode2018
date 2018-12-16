import java.io.File

class PartOne(input: File) {
    init {
        val polymer = input.readLines().first().trim()
        val reduction = PolymerReducer.reduce(polymer)
        println("Reduction is ${reduction.length} bytes long")
    }
}
