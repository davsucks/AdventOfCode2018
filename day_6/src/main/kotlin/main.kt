import java.io.File
import java.time.Duration
import java.time.LocalDateTime

fun main() {
    val start = LocalDateTime.now()
    val file = File("day_6/src/resources/input.txt")
    Thing.doThing(file)
    val end = LocalDateTime.now()

    println("Took ${Duration.between(start, end).toMillis()} milliseconds")
}
