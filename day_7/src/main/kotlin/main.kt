import java.io.File
import java.util.TreeSet

fun main() {
    val runqueue = sortedSetOf<Step>()
    val unstartable = mutableSetOf<Step>()
    File("day_7/src/resources/input.txt")
        .readLines()
        .fold(sortedSetOf<Step>()) { acc, line ->
            val (precondition, postcondition) = line.parsePreconditionAndPostCondition()
            val stepsMatch = { step: Step, condition: Char -> step.name == condition }
            val preconditionsMatch = { step: Step -> stepsMatch(step, precondition) }
            val postconditionsMatch = { step: Step -> stepsMatch(step, postcondition) }

            acc.add(Step(precondition))
            acc.add(Step(postcondition))

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

    val builder = StringBuilder()
    while (runqueue.isNotEmpty()) {
        val currentStep = runqueue.pop()
        currentStep.run()
        builder.append(currentStep.name)
        currentStep.nextSteps.forEach { nextStep ->
            if (nextStep.canRun()) {
                runqueue.add(nextStep)
            }
        }
    }

    println(builder.toString())
}

private fun <E> TreeSet<E>.pop(): E {
    val returnVal = this.first()
    this.remove(returnVal)
    return returnVal
}

private fun <E> Collection<E>.doesNotContain(element: E) = !this.contains(element)

fun <T> TreeSet<T>.contains(predicate: (T) -> Boolean) = this.find(predicate) != null

fun String.parsePreconditionAndPostCondition(): Pair<Char, Char> {
    val precondition = this.toCharArray()[5]
    val postcondition = this.toCharArray()[36]
    return Pair(precondition, postcondition)
}
