import PolymerReducer.capitalize
import PolymerReducer.isOppositeCaseOf
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class PolymerReducerTest {
    @Nested
    inner class DescribeIsOppositeCaseOf {
        @Test
        fun `it is true for a A`() {
            assertThat('a'.isOppositeCaseOf('A')).isTrue()
        }

        @Test
        fun `it is true for A a`() {
            assertThat('A'.isOppositeCaseOf('a')).isTrue()
        }

        @Test
        fun `it is false for a B`() {
            assertThat('a'.isOppositeCaseOf('B')).isFalse()
        }
    }

    @Nested
    inner class DescribeCapitalize {
        @Test
        fun `it capitalizes the character`() {
            assertThat('a'.capitalize()).isEqualTo('A')
        }
    }

    @Nested
    inner class DescribeReduce {
        @Test
        fun `it does not reduce anything`() {
            val reduction = PolymerReducer.reduce("ab")
            assertThat(reduction).isEqualTo("ab")
        }

        @Test
        fun `it does a basic reduction`() {
            val reduction = PolymerReducer.reduce("aA")
            assertThat(reduction).isEqualTo("")
        }

        @Test
        fun `it reduces the first prompt`() {
            val reduction = PolymerReducer.reduce("dabAcCaCBAcCcaDA")
            assertThat(reduction).isEqualTo("dabCBAcaDA")
        }
    }
}
