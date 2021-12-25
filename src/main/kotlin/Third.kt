import kotlin.streams.asSequence

class Third(inputFileName: String, isTest: Boolean) : Common(inputFileName, isTest) {
    override fun solveA() {

        val firstLine = input.readLine()
        val digitsCountArray: Array<Array<Int>> = Array(firstLine.length) { i -> emptyArray() }

        for ((index, value) in firstLine.withIndex()) {
            digitsCountArray[index] = Array(2) { 0 }
            digitsCountArray[index][value.digitToInt()]++
        }

        for (line in input.lines()) {
            for ((index, value) in line.withIndex()) {
                digitsCountArray[index][value.digitToInt()]++
            }
        }

        var mostCommonString = "";
        var leastCommonString = "";
        for (arr in digitsCountArray) {
            if (arr[0] > arr[1]) {
                mostCommonString += "0";
                leastCommonString += "1";
            } else {
                leastCommonString += "0";
                mostCommonString += "1";
            }
        }
        val mostCommon = Integer.parseInt(mostCommonString, 2)
        val leastCommon = Integer.parseInt(leastCommonString, 2)
        println(mostCommon * leastCommon);
    }

    override fun solveB() {
        val lines = input.lines().asSequence().toList()
        val length = lines[0].length

        val oxygen = lines.toMutableList()
        for (i in 0 until length) {
            if (oxygen.size == 1) {
                break;
            }
            val mcb = mostCommonBit(oxygen, i)
            oxygen.removeAll { s -> s[i] != mcb }
        }

        val co2 = lines.toMutableList()
        for (i in 0 until length) {
            if (co2.size == 1) {
                break;
            }
            val lcb = leastCommonBit(co2, i)
            co2.removeAll { s -> s[i] != lcb }
        }
        println(Integer.parseInt(oxygen[0], 2) * Integer.parseInt(co2[0], 2))
    }

    private fun mostCommonBit(strings: List<String>, position: Int): Char {
        val average = strings.map { s -> s[position] }
            .map { c -> Integer.parseInt(c.toString()) }
            .average()
        return if (average >= 0.5) '1' else '0'
    }

    private fun leastCommonBit(strings: List<String>, position: Int): Char {
        val average = strings.map { s -> s[position] }
            .map { c -> Integer.parseInt(c.toString()) }
            .average()
        return if (average >= 0.5) '0' else '1'
    }
}