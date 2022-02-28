package neilgilbert.gallardo.tictactoe_p3_animations_finaltouches.game

class Game {
    var running = false
    var gridTicVal = mutableListOf<Cell>()
    var curSign = 1

    fun start()
    {
        running = true;
        gridTicVal = mutableListOf(
            Cell("1x1", 0),
            Cell("1x2", 0),
            Cell("1x3", 0),
            Cell("2x1", 0),
            Cell("2x2", 0),
            Cell("2x3", 0),
            Cell("3x1", 0),
            Cell("3x2", 0),
            Cell("3x3", 0)
        )
        curSign = 1
        //println("[Game] Started")
    }
    fun end()
    {
        running = false
        gridTicVal = mutableListOf<Cell>()
        //println("[Game] Ended")
    }
    fun mark(loc : String) : Boolean
    {
        var targetCell : Cell? = gridTicVal.find { it.loc == loc }

        if(targetCell!=null && targetCell.tic==0)
        {
            targetCell.tic = this.curSign
            gridTicVal[gridTicVal.indexOf(targetCell)] = targetCell

            curSign *= -1
            //println("[Game] $loc marked, turn $curTurn")
            return true
        } else {
            return false
        }
    }

    fun checkWinningCondition(sign : Int) : Boolean
    {
        if( gridTicVal.indexOf(Cell("1x1", sign)) > -1 &&
            gridTicVal.indexOf(Cell("1x2", sign)) > -1 &&
            gridTicVal.indexOf(Cell("1x3", sign)) > -1)
            return true
        if( gridTicVal.indexOf(Cell("2x1", sign)) > -1 &&
            gridTicVal.indexOf(Cell("2x2", sign)) > -1 &&
            gridTicVal.indexOf(Cell("2x3", sign)) > -1)
            return true
        if( gridTicVal.indexOf(Cell("3x1", sign)) > -1 &&
            gridTicVal.indexOf(Cell("3x2", sign)) > -1 &&
            gridTicVal.indexOf(Cell("3x3", sign)) > -1)
            return true
        if( gridTicVal.indexOf(Cell("1x1", sign)) > -1 &&
            gridTicVal.indexOf(Cell("2x1", sign)) > -1 &&
            gridTicVal.indexOf(Cell("3x1", sign)) > -1)
            return true
        if( gridTicVal.indexOf(Cell("1x2", sign)) > -1 &&
            gridTicVal.indexOf(Cell("2x2", sign)) > -1 &&
            gridTicVal.indexOf(Cell("3x2", sign)) > -1)
            return true
        if( gridTicVal.indexOf(Cell("1x3", sign)) > -1 &&
            gridTicVal.indexOf(Cell("2x3", sign)) > -1 &&
            gridTicVal.indexOf(Cell("3x3", sign)) > -1)
            return true
        if( gridTicVal.indexOf(Cell("1x1", sign)) > -1 &&
            gridTicVal.indexOf(Cell("2x2", sign)) > -1 &&
            gridTicVal.indexOf(Cell("3x3", sign)) > -1)
            return true
        if( gridTicVal.indexOf(Cell("1x3", sign)) > -1 &&
            gridTicVal.indexOf(Cell("2x2", sign)) > -1 &&
            gridTicVal.indexOf(Cell("3x1", sign)) > -1)
            return true
        return false
    }
    fun checkDeadLockCondition() : Boolean
    {
        val emptyTicks = existsCount(Cell("1x1", 0)) +
                existsCount(Cell("1x2", 0)) +
                existsCount(Cell("1x3", 0)) +
                existsCount(Cell("2x1", 0)) +
                existsCount(Cell("2x2", 0)) +
                existsCount(Cell("2x3", 0)) +
                existsCount(Cell("3x1", 0)) +
                existsCount(Cell("3x2", 0)) +
                existsCount(Cell("3x3", 0))
        if(emptyTicks<=0)
            return true
        return false
    }
    fun existsCount(c : Cell) : Int
    {
        if(gridTicVal.indexOf(c) > -1)
            return 1
        return 0
    }

    //[Phase 1] Print grid to console
    fun printGridToConsole()
    {
        println()
        print("|"); print(getSign("1x1")); print("|"); print(getSign("1x2")); print("|"); print(getSign("1x3"));print("|");println();
        print("|"); print(getSign("2x1")); print("|"); print(getSign("2x2")); print("|"); print(getSign("2x3"));print("|");println();
        print("|"); print(getSign("3x1")); print("|"); print(getSign("3x2")); print("|"); print(getSign("3x3"));print("|");println();
    }

    //[Phase 1] Get sign of tic
    fun getSign(loc : String) : String
    {
        var cell : Cell? = gridTicVal.find { it.loc == loc }
        if (cell != null) {
            return if(cell.tic!=0) if(cell.tic==1) "O" else "X" else " "
        } else {
            return " "
        }
    }

    //[Phase 2] Get cell tic
    fun getTic(loc : String) : Int
    {
        var cell : Cell? = gridTicVal.find { it.loc == loc }
        return if(cell!=null) cell.tic else 0
    }
}