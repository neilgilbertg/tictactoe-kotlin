import game.Game

fun main(args: Array<String>) {
    val tttGame = Game()
    tttGame.start()
    while (tttGame.running)
    {
        // Print grid
        tttGame.printGridToConsole()

        // Check win
        if(tttGame.checkWinningCondition(tttGame.curSign*-1))
        {
            var sign = if(tttGame.curSign!=0) if(tttGame.curSign*-1==1) "O" else "X" else " "
            println("$sign side wins!!!")
            println("New game starting, press enter to start...")
            readLine()

            // Reset and print grid
            tttGame.start()
            tttGame.printGridToConsole()
        }
        else if(tttGame.checkDeadLockCondition())
        {
            println("Draw, resetting, please enter to start...")
            readLine()

            // Reset and print grid
            tttGame.start()
            tttGame.printGridToConsole()
        }

        // Input
        val sign = if (tttGame.curSign==1) "O" else "X"
        println("Mark a grid ($sign):");
        val input = readLine().toString()
        if(input.trim().lowercase() == "end")
        {
            tttGame.end()
            break
        }
        else if(!tttGame.mark(input))
            println("Invalid input try again");
    }

}