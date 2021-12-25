class Fourth(inputFileName: String, isTest: Boolean) : Common(inputFileName, isTest) {
    override fun solveA() {

        val bingoSeq = input.readLine().split(',').map { s -> Integer.parseInt(s) }

        val bingoBoards = input.readLines()
            .asSequence()
            .filter { s -> s.isNotBlank() }
            .chunked(5)
            .toList()
            .map { e -> linesToBoard(e) }
            .toMutableList()

        val visited = mutableListOf<Int>()
        for (i in bingoSeq) {
            for ((boardIndex, b) in bingoBoards.withIndex()) {
                checkNumber(i, b)
                if (!visited.contains(boardIndex)) {
                    val win = checkBoard(b)
                    if (win) {
                        println(countBoard(b) * i)
                        visited.add(boardIndex)
                    }
                }
            }

        }
    }

    private fun linesToBoard(list: List<String>): Array<Array<Pair<Int, Boolean>?>?> {
        val board = arrayOfNulls<Array<Pair<Int, Boolean>?>>(5)
        for ((i, line) in list.withIndex()) {
            val row = arrayOfNulls<Pair<Int, Boolean>>(5)
            val rawRow = line.split(' ').filter { s -> s.isNotBlank() }.map { s -> Integer.parseInt(s) }
            for ((j, number) in rawRow.withIndex()) {
                val pair = Pair(number, false)
                row[j] = pair
            }
            board[i] = row
        }
        return board
    }

    private fun checkNumber(n: Int, board: Array<Array<Pair<Int, Boolean>?>?>) {
        for (i in board.indices) {
            for (j in board[i]!!.indices) {
                if (board[i]!![j]!!.first == n) {
                    board[i]!![j] = board[i]!![j]!!.copy(second = true)
                    return
                }
            }
        }
    }

    private fun checkBoard(board: Array<Array<Pair<Int, Boolean>?>?>): Boolean {
        for (i in board.indices) {
            var w = true
            for (j in board[i]!!.indices) {
                w = board[i]!![j]!!.second
                if (!w) {
                    break
                }
            }
            if (w) {
                return true
            }
        }

        for (i in board.indices) {
            var w = true
            for (j in board[i]!!.indices) {
                w = board[j]!![i]!!.second
                if (!w) {
                    break
                }
            }
            if (w) {
                return true
            }
        }

        return false
    }

    private fun countBoard(board: Array<Array<Pair<Int, Boolean>?>?>): Int {
        return board.requireNoNulls().flatten().requireNoNulls()
            .filter { p -> !p.second }.map { p -> p.first }.sum()
    }

    override fun solveB() {
        TODO("Not yet implemented")
    }
}