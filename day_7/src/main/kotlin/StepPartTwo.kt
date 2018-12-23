private val stepLength: Map<Char, Int> = ('A'..'Z').zip(1..26).toMap()
private const val baseTime = 60

data class StepPartTwo(val name: Char) : Comparable<StepPartTwo> {
    val executionContext = ExecutionContext(baseTime + stepLength[name]!!)
    val preconditions = mutableListOf<StepPartTwo>()
    val nextSteps = mutableListOf<StepPartTwo>()

    override fun compareTo(other: StepPartTwo) = name.compareTo(other.name)
    override fun toString() = name.toString()

    fun start() {
        if (!canRun()) throw Exception("Cannot be run!")
        executionContext.start()
    }

    fun update() {
        executionContext.update()
    }

    fun canRun() = preconditions.all { it.hasRun() }

    fun hasRun() = executionContext.hasRun
}
