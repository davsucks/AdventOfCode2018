import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 *  ASSUMPTIONS:
 *      input files are never empty
 *      input files always start with a guard
 */
class PartTwo(input: File) {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm")
    private val guards = mutableListOf<Guard>()
    private val readLines = input
        .readLines()
        .filter { !it.isBlank() }
        .sorted()
        .listIterator()

    init {
        // ASSUMPTION: Files always start with a guard
        val currentGuard = parseGuard(readLines.next())
        consumeAllGuards(currentGuard)

        val sleepiestGuard =
            guards.sortedByDescending { it.sleepiestMinute.second }.first()
        println("Guard ${sleepiestGuard.id}:")
        println("\twas asleep for ${sleepiestGuard.minutesAsleep} minutes")
        println("\twas most tired at minute ${sleepiestGuard.sleepiestMinute.first}")
    }

    private tailrec fun consumeAllGuards(guard: Guard) {
        if (!readLines.hasNext()) return
        val rawLine = readLines.next()
        if (rawLine.contains("Guard")) {
            consumeAllGuards(parseGuard(rawLine))
        } else {
            val asleepTimestamp = parseLocalDateTime(rawLine)
            val lineTwo = readLines.next()
            val awakeTimestamp = parseLocalDateTime(lineTwo)
            guard.addRange(asleepTimestamp.minute..awakeTimestamp.minute)

            // we read one extra line previously, need to back it up
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
        val sleepiestMinute: Pair<Int, Int>
            get() =
                if (asleepRanges.isEmpty()) Pair(0, 0) else asleepRanges
                    .fold(mutableMapOf<Int, Int>()) { acc, intRange ->
                        intRange.associateWithTo(acc) { minute ->
                            acc.getOrDefault(minute, 0) + 1
                        }
                    }
                    .toList()
                    .sortedByDescending { (_, value) -> value }
                    .first()

        fun addRange(range: IntRange) = asleepRanges.add(range)

        private fun IntRange.difference(): Int = endInclusive - start
    }
}
