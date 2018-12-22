import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MainKtTest {
    @Test
    fun `should parse precondition and postcondition`() {
        val input = "Step A must be finished before step B can begin."
        assertThat(input.parsePreconditionAndPostCondition()).isEqualTo(Pair('A', 'B'))
    }
}
