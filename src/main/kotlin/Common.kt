import java.io.BufferedReader

abstract class Common(var inputFileName: String, isTest: Boolean) {
    var input: BufferedReader;



    init {
        inputFileName = "$inputFileName/$inputFileName" // files are in resources/{input}/
        if (isTest) {
            inputFileName = inputFileName.plus("_test")
        }
        input = javaClass.getResourceAsStream(inputFileName).bufferedReader()
    }

    abstract fun solveA()
    abstract fun solveB()
}