import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class ExecutionContextTest {
    @Test
    fun `should throw if trying to update an execution context that hasn't started yet`() {
        val executionContext = ExecutionContext(0)
        assertThrows<Exception> {
            executionContext.update()
        }
    }

    @Test
    fun `should no longer be running after executing for exactly as many ticks as length of execution context`() {
        val executionContext = ExecutionContext(1)
        executionContext.start()
        executionContext.update()
        assertThat(executionContext.hasRun).isTrue()
    }

    @Test
    fun `should be running during execution`() {
        val executionContext = ExecutionContext(2)
        executionContext.start()
        executionContext.update()
        assertThat(executionContext.isRunning).isTrue()
        assertThat(executionContext.hasRun).isFalse()
    }
}
