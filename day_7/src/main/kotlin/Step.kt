data class Step(val name: Char) : Comparable<Step> {
    val nextSteps: MutableList<Step> = mutableListOf()
    var hasRun = false
    val preconditions: MutableList<Step> = mutableListOf()
    override fun compareTo(other: Step) = name.compareTo(other.name)
    override fun toString() = name.toString()
    fun run() {
        if (!canRun()) throw Exception("Cannot be run!")
        hasRun = true
    }
    fun canRun() = preconditions.all { it.hasRun }
}
