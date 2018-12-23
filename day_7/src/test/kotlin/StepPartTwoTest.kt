import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class StepPartTwoTest {
    companion object {
        @Suppress("unused")
        @JvmStatic
        fun generateMapping(): Stream<Arguments> = ('A'..'Z').zip(61..86)
            .map { (name, value) -> Arguments.of(name, value) }
            .stream()
    }

    @ParameterizedTest
    @MethodSource("generateMapping")
    fun `should map steps by name`(name: Char, expectedTimeToRun: Int) {
        assertThat(StepPartTwo(name).executionContext.timeToRun).isEqualTo(expectedTimeToRun)
    }

    @Nested
    inner class DescribeCanRun {
        @Test
        fun `can be run with no preconditions`() {
            val stepCanRun = Step('A')
            assertThat(stepCanRun.canRun()).isTrue()
        }

        @Test
        fun `can be run with one precondition that has run`() {
            val stepCanRun = Step('A')
            val precondition = Step('B')
            stepCanRun.preconditions.add(precondition)
            precondition.run()
            assertThat(stepCanRun.canRun()).isTrue()
        }

        @Test
        fun `can not be run if one precondition has not run`() {
            val stepCannotRun = Step('A')
            val precondition = Step('B')
            val unrunPrecondition = Step('C')
            stepCannotRun.preconditions.add(precondition)
            stepCannotRun.preconditions.add(unrunPrecondition)
            precondition.run()
            assertThat(stepCannotRun.canRun()).isFalse()
        }

        @Test
        fun `will throw if trying to run with un-run preconditions`() {
            val stepCannotRun = Step('A')
            val unrunPrecondition = Step('B')
            stepCannotRun.preconditions.add(unrunPrecondition)
            assertThrows<Exception> {
                stepCannotRun.run()
            }
        }
    }
}
