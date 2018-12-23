class Worker {
    var busy = false
    var assignedTask: StepPartTwo? = null

    fun assignTask(task: StepPartTwo) {
        busy = true
        assignedTask = task
        task.start()
    }

    fun update()  {
        if (!busy) throw Exception("Cannot update worker that is not busy!")
        assignedTask!!.update()
    }

    fun isFinished() = assignedTask?.hasRun() ?: true

    fun unassignTask() {
        if (!isFinished()) throw Exception("Cannot unassign task while still working!")
        busy = false
        assignedTask = null
    }
}
