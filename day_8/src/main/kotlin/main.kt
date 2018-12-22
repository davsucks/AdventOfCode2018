import java.io.File

fun main() {
    val input = File("day_8/src/resources/inputTwo.txt")
        .readText()
        .trim()
        .split(' ')
        .map { it.toInt() }.listIterator()
    val tree = readTree(input)
    println("tree sum is: ${tree.sumMetadata()}")
}

fun readTree(iterator: Iterator<Int>): Node {
    val numChildNodes = iterator.next()
    val numMetadataEntries = iterator.next()
    return if (numChildNodes == 0) {
        val metadata = (1..numMetadataEntries).map { iterator.next() }
        Node(listOf(), metadata)
    } else {
        val childrenNodes = (1..numChildNodes).map { readTree(iterator) }
        val metadata = (1..numMetadataEntries).map { iterator.next() }
        return Node(childrenNodes, metadata)
    }
}
