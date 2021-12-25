class First(inputFileName: String, isTest: Boolean = false) : Common(inputFileName, isTest) {

    override fun solveA() {
        var counter = 0
        var previousNumber: Int? = null

        for (line in input.lines()) {
            val number = Integer.valueOf(line)
            if (previousNumber != null && number > previousNumber) {
                counter++
            }
            previousNumber = number
        }
        println(counter)
    }

    override fun solveB() {
        val result = input.readLines()
            .map { Integer.valueOf(it) }
            .windowed(3, step = 1, partialWindows = false)
            .map { it.sum() }
            .windowed(2, 1)
            .fold(initial = 0, operation = { acc, next ->
                acc + (if (next[1] > next[0]) 1 else 0)
            })
        println(result)
    }
}