data class Node(val id: Int = -1, val point: Point = Point(-1, -1)) {
    constructor(id: Int, rawInput: String) : this(id, Point(rawInput))
}
