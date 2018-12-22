data class Node(val children: List<Node>, val metadata: List<Int>) {
    fun sumMetadata(): Int = children.fold(0) { acc, node ->
        acc + node.sumMetadata()
    } + metadata.sum()
}
