import java.io.File
import java.util.TreeSet

fun main() {
    val runnableQueue = buildSetPartTwo()
    val freeWorkers = (1..4).map { Worker() }.toMutableList()
    val busyWorkers = mutableListOf<Worker>()
    var elapsedSeconds = 0

    while (runnableQueue.isNotEmpty() || busyWorkers.isNotEmpty()) {
        println("Second: $elapsedSeconds")
        // assign all workers
        while (freeWorkers.isNotEmpty() && runnableQueue.isNotEmpty()) {
            val freeWorker = freeWorkers.pop()
            val nextTask = runnableQueue.pop()

            println("Assigning worker to task ${nextTask.name}")
            freeWorker.assignTask(nextTask)

            busyWorkers.add(freeWorker)
        }

        // update all workers
        busyWorkers.forEach { busyWorker ->
            println("Updating worker")
            busyWorker.update()
            if (busyWorker.isFinished()) {
                println("Worker is finished")
                // add all next steps
                busyWorker.assignedTask!!.nextSteps.filterTo(runnableQueue) { it.canRun() }
                // free up worker
                busyWorker.unassignTask()
            }
        }
        busyWorkers.filterTo(freeWorkers) { it.isFinished() }
        busyWorkers.removeAll { it.isFinished() }

        elapsedSeconds += 1
    }
    println(elapsedSeconds)
}

fun buildSetPartTwo(): TreeSet<StepPartTwo> {
    val runqueue = sortedSetOf<StepPartTwo>()
    val unstartable = mutableSetOf<StepPartTwo>()
    File("day_7/src/resources/input.txt")
        .readLines()
        .fold(sortedSetOf<StepPartTwo>()) { acc, line ->
            val (precondition, postcondition) = line.parsePreconditionAndPostCondition()
            val stepsMatch = { step: StepPartTwo, condition: Char -> step.name == condition }
            val preconditionsMatch = { step: StepPartTwo -> stepsMatch(step, precondition) }
            val postconditionsMatch = { step: StepPartTwo -> stepsMatch(step, postcondition) }

            acc.add(StepPartTwo(precondition))
            acc.add(StepPartTwo(postcondition))

            val preconditionStep = acc.find(preconditionsMatch)!!
            val postconditionStep = acc.find(postconditionsMatch)!!

            preconditionStep.nextSteps.add(postconditionStep)
            postconditionStep.preconditions.add(preconditionStep)

            if (unstartable.doesNotContain(preconditionStep)) {
                runqueue.add(preconditionStep)
            }

            runqueue.removeIf(postconditionsMatch)
            unstartable.add(postconditionStep)
            acc
        }
    return runqueue
}
