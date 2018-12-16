import java.io.File

class BoundingBox(
    private val nodesSortedByX: List<Node>,
    nodesSortedByY: List<Node>
) {
    val allPoints: List<Point>
    val boundingNodes: List<Node>

    private val highestXValue: Int
    private val lowestXValue: Int
    private val lowestYValue: Int
    private val highestYValue: Int

    init {
        val leftmostNode = nodesSortedByX.first()
        val lowestNode = nodesSortedByY.first()
        val rightmostNode = nodesSortedByX.last()
        val highestNode = nodesSortedByY.last()

        lowestXValue = leftmostNode.point.x
        lowestYValue = lowestNode.point.y
        highestXValue = rightmostNode.point.x
        highestYValue = highestNode.point.y


        boundingNodes = listOf(leftmostNode, lowestNode, rightmostNode, highestNode)
        println("Bounding Nodes:")
        boundingNodes.forEach(::println)
        allPoints = (lowestXValue..highestXValue).map { xValue ->
            (lowestYValue..highestYValue).map { yValue -> Point(xValue, yValue) }
        }.flatten().sortedWith(compareBy<Point> { it.y }.thenBy { it.x })
    }

    fun pointIsOnBorder(point: Point) =
        point.x == lowestYValue || point.x == highestXValue || point.y == highestYValue || point.y == lowestYValue
}
