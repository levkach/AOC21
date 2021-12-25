class Second(inputFileName: String, isTest: Boolean) : Common(inputFileName, isTest) {

    private var aliasToFunction = HashMap<String, (Int) -> Unit>()

    override fun solveA() {
        var (x, y) = listOf(0, 0)
        for (line in input.lines()) {
            val direction = line.split(" ")[0]
            val amount = Integer.valueOf(line.split(" ")[1])
            if (direction == "forward") {
                x += amount
            } else {
                y += if (direction == "down") amount else -amount
            }
        }
        println(x * y)
    }

    fun solveAWithFunctions() {
        aliasToFunction = HashMap()
        var (x, y) = listOf(0, 0)
        aliasToFunction["forward"] = { amount -> x += amount }
        aliasToFunction["down"] = { amount -> y += amount }
        aliasToFunction["up"] = { amount -> y -= amount }
        for (line in input.lines()) {
            val (direction: String, amount: String) = line.split(" ")
            aliasToFunction[direction]?.invoke(Integer.valueOf(amount))
        }
        println(x * y)
    }

    override fun solveB() {
        aliasToFunction = HashMap()
        var (x, y, aim) = listOf(0, 0, 0)
        aliasToFunction["forward"] = { amount -> y += aim * amount; x += amount }
        aliasToFunction["down"] = { amount -> aim += amount }
        aliasToFunction["up"] = { amount -> aim -= amount }
        for (line in input.lines()) {
            val (direction: String, amount: String) = line.split(" ")
            aliasToFunction[direction]?.invoke(Integer.valueOf(amount))
        }
        println(x * y)
        val a = 0 until 123
        IntRange
    }

}