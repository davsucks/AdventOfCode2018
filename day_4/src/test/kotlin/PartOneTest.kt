import PartOne.Guard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PartOneTest {
    @Test
    fun `two guards with different lists are equal`() {
        val id = "Guard One"
        val guardOne = Guard(id)
        val newGuardOne = Guard(id)
        newGuardOne.addRange(1..3)

        assertThat(guardOne).isEqualTo(newGuardOne)
    }

    @Test
    fun `guards keep track of how much time they are asleep`() {
        val myGuard = Guard("Guard One")
        myGuard.addRange(1..3)
        myGuard.addRange(2..4)
        assertThat(myGuard.minutesAsleep).isEqualTo(4)
    }

    @Test
    fun `should find the minutes the guard is asleep longest`() {
        val myGuard = Guard("Guard One")
        myGuard.addRange(2..3)
        myGuard.addRange(2..4)
        assertThat(myGuard.sleepiestMinute).isEqualTo(2)
    }
}
