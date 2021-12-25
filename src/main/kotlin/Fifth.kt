import kotlin.streams.toList

class Fifth(inputFileName: String, isTest: Boolean) : Common(inputFileName, isTest) {
    override fun solveA() {

        val inp = input.lines().toList()
        val listListOfPairs = inp.asSequence()
            .map { s -> s.split(" -> ") }
            .map { list ->
                list.map { xyString ->
                    Pair(Integer.parseInt(xyString.split(",")[0]), Integer.parseInt(xyString.split(",")[1]))
                }
            }
//            .filter { pairs -> pairs[0].first == pairs[1].first || pairs[0].second == pairs[1].second } // filter out for A
            .map { list -> list.sortedBy { pair -> pair.first } } // order by X
            .map { list -> list.sortedBy { pair -> pair.second } } // order by Y
            .toList()
        val maxX = listListOfPairs.flatten().maxOf { p -> p.first }
        val maxY = listListOfPairs.flatten().maxOf { p -> p.second }

        val list = Array(maxX + 1) { IntArray(maxY + 1) { 0 } }
        for (p in listListOfPairs) {
            val (startPoint, endPoint) = p
            if (startPoint.first == endPoint.first) {
                for (y in startPoint.second until endPoint.second + 1) {
                    list[startPoint.first][y]++
                }
            } else if (startPoint.second == endPoint.second) {
                for (x in startPoint.first until endPoint.first + 1) {
                    list[x][startPoint.second]++
                }
            } else {
                // at this point it's guaranteed that startPoint.Y < endPoint.Y
                val step = if (startPoint.first < endPoint.first) 1 else -1
                var x = startPoint.first
                for (y in startPoint.second until endPoint.second + 1) {
                    list[x][y]++
                    x += step
                }
            }
        }

        var count = 0
        for (x in 0..maxX) {
            for (y in 0..maxY) {
                if (list[x][y] >= 2) {
                    count++
                }
            }
        }
        println(count)
    }

    override fun solveB() {
        TODO("Not yet implemented")

    }
}