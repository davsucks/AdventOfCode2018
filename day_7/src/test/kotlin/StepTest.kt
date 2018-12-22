import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class StepTest {
    @Nested
    inner class DescribeOrdering {
        @Test
        fun `it should order according to name and not by next steps`() {
            val stepOne = Step('A')
            stepOne.nextSteps.add(Step('B'))
            stepOne.nextSteps.add(Step('C'))
            val stepTwo = Step('B')
            val stepThree = Step('C')
            stepThree.nextSteps.add(Step('A'))

            assertThat(listOf(stepTwo, stepThree, stepOne).sorted()).isEqualTo(listOf(stepOne, stepTwo, stepThree))
        }
    }

    @Test
    fun `should be equal based on name only`() {
        val stepOne = Step('A')
        stepOne.nextSteps.add(Step('B'))
        stepOne.nextSteps.add(Step('C'))
        val stepTwo = Step('A')

        assertThat(stepOne).isEqualTo(stepTwo)
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
