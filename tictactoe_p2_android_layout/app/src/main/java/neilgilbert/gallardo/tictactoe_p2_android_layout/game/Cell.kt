package neilgilbert.gallardo.tictactoe_p2_android_layout.game

data class Cell(
    // loc valid values: 1x1, 1x2, 1x3, 2x1, 2x2, 2x3, 3x1, 3x2, 3x3
    val loc : String,
    // tic valid values: 0=neutral, 1=O, -1=X
    var tic : Int
)
