import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


/**
 *  ASSUMPTIONS:
 *      input files are never empty
 *      input files always start with a guard
 *      Guards always fall asleep at least once during their shift
 */
class PartOne(input: File) {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm")
    private val guards = mutableListOf<Guard>()
    private val readLines =
        input.readLines(Charsets.UTF_8).sorted().listIterator()

    init {
        val currentGuard = parseGuard(readLines.next())
        consumeAllGuards(currentGuard)
        guards.forEach {
            println("Guard ${it.id} was asleep for ${it.minutesAsleep} minutes total")
        }
    }

    private tailrec fun consumeAllGuards(guard: Guard) {
        val asleepTimestamp = parseLocalDateTime(readLines.next())
        val awakeTimestamp = parseLocalDateTime(readLines.next())
        guard.addRange(asleepTimestamp.minute..awakeTimestamp.minute)

        if (!readLines.hasNext()) return
        val rawLine = readLines.next()
        if (rawLine.contains("Guard")) {
            consumeAllGuards(parseGuard(rawLine))
        } else {
            // we read one extra line previously, need to back it up
            readLines.previous()
            consumeAllGuards(guard)
        }
    }

    private fun parseGuard(rawLine: String): Guard {
        val words = rawLine.split(" ")
        val guardId = words[3]
        val nullableGuard = guards.find { it.id == guardId }
        return if (nullableGuard == null) {
            val newGuard = Guard(guardId)
            guards.add(newGuard)
            newGuard
        } else {
            nullableGuard
        }
    }

    private fun parseLocalDateTime(rawTimestamp: String) =
        LocalDateTime.parse(rawTimestamp.substring(1..16), formatter)

    data class Guard(val id: String) {
        private val asleepRanges = mutableListOf<IntRange>()
        val minutesAsleep: Int
            get() = asleepRanges.fold(0) { acc, intRange -> acc + intRange.difference() }

        fun addRange(range: IntRange) = asleepRanges.add(range)

        private fun IntRange.difference(): Int = endInclusive - start
    }
}

