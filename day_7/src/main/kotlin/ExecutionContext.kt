class ExecutionContext(val timeToRun: Int) {
    var isRunning = false
    var hasRun = false

    private var elapsedSeconds = 0

    fun start() {
        isRunning = true
    }

    fun update() {
        if (!isRunning) throw Exception("Cannot update task that isn't running!")
        elapsedSeconds += 1
        if (isDoneExecuting()) {
            isRunning = false
            hasRun = true
        }
    }

    private fun isDoneExecuting() = if (hasRun) true else { elapsedSeconds == timeToRun }
}
