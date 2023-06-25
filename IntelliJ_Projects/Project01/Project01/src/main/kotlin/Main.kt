fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    val numbs: List<Int> = (0..99).toList()
    println("list $numbs")
    println("list ${numbs.filter { it % 2 == 0 }}")
    println("list ${numbs.filter { it % 2 != 0 }}")
    println("list ${numbs.filter { it % 3 == 0 && it % 5 == 0 }}")
    println("list ${numbs.filter { it % 3 == 0 && it % 5 == 0 }.sortedDescending()}")

}