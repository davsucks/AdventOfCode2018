import kotlin.math.abs

data class Point(val x: Int, val y: Int) {
    constructor(string: String) : this(string.split(", ")[0].toInt(), string.split(", ")[1].toInt())

    fun distanceTo(point: Point) = abs(x - point.x) + abs(y - point.y)
    fun closestNode(nodes: List<Node>): Node {
        val closestNodes = nodes.sortedBy { this.distanceTo(it.point) }
        return if (closestNodes.size > 1 && distanceTo(closestNodes[0].point) == distanceTo(closestNodes[1].point)) Node() else closestNodes[0]
    }
}
