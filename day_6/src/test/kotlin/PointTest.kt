import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class PointTest {
    @Test
    fun `it parses a string as input`() {
        assertThat(Point("1, 2")).isEqualTo(Point(1, 2))
    }

    @Nested
    inner class DescribeDistanceTo {
        @Test
        fun `it calculates manhattan distance to another point`() {
            val pointA = Point(1, 2)
            val pointB = Point(3, 4)
            assertThat(pointA.distanceTo(pointB)).isEqualTo(4)
        }
    }

    @Nested
    inner class DescribeClosestNode {
        @Test
        fun `it determines the closest node`() {
            val pointA = Point(1, 2)
            val nodeOne = Node(1, Point(10, 3))
            val nodeTwo = Node(2, Point(4, 5))

            assertThat(pointA.closestNode(listOf(nodeOne, nodeTwo))).isEqualTo(nodeTwo)
        }

        @Test
        fun `if two nodes are equidistant, returns 'null' object`() {
            val pointA = Point(1, 2)
            val nodeOne = Node(1, Point(0, 1))
            val nodeTwo = Node(2, Point(2, 3))

            assertThat(pointA.closestNode(listOf(nodeOne, nodeTwo))).isEqualTo(Node())
        }
    }
}
