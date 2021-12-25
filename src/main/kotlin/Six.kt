class Six(inputFileName: String, isTest: Boolean) : Common(inputFileName, isTest) {

    class Fish(private var toNextReproduce: Int, private val master: FishMaster) {
        companion object {
            const val TIME_TO_REPRODUCE = 6
        }

        internal fun iterate() {
            this.toNextReproduce--
            if (this.toNextReproduce == -1) {
                this.masterMeMustReproduce()
            }
        }

        private fun masterMeMustReproduce() {
            this.master.gotchaSalmo(this)
        }

        internal fun resetTimer() {
            this.toNextReproduce = TIME_TO_REPRODUCE
        }


    }

    class FishMaster {
        val fishes = mutableListOf<Fish>()
        private val newJoiners = mutableListOf<Fish>()

        fun addFishes(fishes: List<Fish>) {
            this.fishes.addAll(fishes)
        }

        fun gotchaSalmo(fish: Fish) {
            fish.resetTimer()
            this.newJoiners.add(Fish(Fish.TIME_TO_REPRODUCE + 2, this))
        }

        fun anotherDay() {
            for (fish in fishes) {
                fish.iterate()
            }
            this.fishes.addAll(this.newJoiners)
            this.newJoiners.clear()
        }


    }

    constructor(isTest: Boolean) : this("six", isTest)

    var days: Int = 80;

    override fun solveA() {
        val master = FishMaster()
        val fishes = input.readLine()
            .split(",")
            .map { Integer.parseInt(it) }
            .map { Fish(it, master) }
        master.addFishes(fishes)

        for (i in 0 until days) {
            master.anotherDay()
        }
        println(master.fishes.size)
    }

    override fun solveB() {
        days = 256;
        solveA(); // oops, O(k^n)
    }
}