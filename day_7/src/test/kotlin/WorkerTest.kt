import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkerTest {
    @Test
    fun `a worker starts off not busy`() {
        val freeWorker = Worker()
        assertThat(freeWorker.busy).isFalse()
        assertThat(freeWorker.assignedTask).isNull()
    }

    @Test
    fun `a worker can be assigned a task`() {
        val assignedWorker = Worker()
        val task = StepPartTwo('A')
        assignedWorker.assignTask(task)
        assertThat(assignedWorker.busy).isTrue()
        assertThat(assignedWorker.assignedTask).isEqualTo(task)
    }
}
