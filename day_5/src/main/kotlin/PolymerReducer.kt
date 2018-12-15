object PolymerReducer {
    fun reduce(polymer: String): String =
        reduceHelper(mutableListOf(), polymer.toList().listIterator())

    private tailrec fun reduceHelper(
        reduction: List<Char>,
        iterator: Iterator<Char>
    ): String {
        if (!iterator.hasNext()) return reduction.joinToString("")
        val currentChar = iterator.next()
        if (reduction.isNotEmpty() && reduction.last().isOppositeCaseOf(currentChar)) {
            return reduceHelper(reduction.dropLast(1), iterator)
        }
        return reduceHelper(reduction + currentChar, iterator)
    }

    fun Char.isOppositeCaseOf(other: Char) =
        this != other && (capitalize() == other || this == other.capitalize())

    fun Char.capitalize(): Char = toString().capitalize().toCharArray()[0]
}
