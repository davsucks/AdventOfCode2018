import java.io.File

object Thing {
    fun doThing(file: File) {
        val allNodes = file.readLines().mapIndexed { index, rawInput -> Node(index, rawInput) }
        val nodesSortedByX = allNodes.sortedBy { it.point.x }
        val nodesSortedByY = allNodes.sortedBy { it.point.y }

        val boundingBox = BoundingBox(nodesSortedByX = nodesSortedByX, nodesSortedByY = nodesSortedByY)

        // map coordinates to closest coordinate
        // filter out the four bounding points
        // count letters
        val borderNodes = allNodes.filter { boundingBox.pointIsOnBorder(it.point) }
        val borderTerritoryNodeIds = mutableSetOf<Int>()
        val counts = boundingBox.allPoints
            .map {
                val closestNodeId = it.closestNode(allNodes).id
                if (boundingBox.pointIsOnBorder(it)) borderTerritoryNodeIds.add(closestNodeId)
                closestNodeId
            }
            .filterNot { it == Node().id }
            .filterNot { it in borderNodes.map(Node::id) }
            .filterNot { it in borderTerritoryNodeIds }
            .groupingBy { it }
            .eachCount()
            .sortedByValueDescending()
        println(counts)
    }

    private fun <K, V : Comparable<V>> Map<K, V>.sortedByValueDescending() =
        toList().sortedByDescending { (_, value) -> value }.toMap()
}
