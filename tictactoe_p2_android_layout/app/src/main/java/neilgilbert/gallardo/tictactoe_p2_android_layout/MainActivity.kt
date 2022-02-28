package neilgilbert.gallardo.tictactoe_p2_android_layout

import android.app.AlertDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import neilgilbert.gallardo.tictactoe_p2_android_layout.game.Cell
import neilgilbert.gallardo.tictactoe_p2_android_layout.game.Game

class MainActivity : AppCompatActivity() {
    var btnList = mutableMapOf<String, ImageButton>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tttGame = Game()
        tttGame.start()

        setContentView(R.layout.activity_main)
        btnList.put("1x1", findViewById<ImageButton>(R.id.c1x1))
        btnList.put("1x2", findViewById<ImageButton>(R.id.c1x2))
        btnList.put("1x3", findViewById<ImageButton>(R.id.c1x3))
        btnList.put("2x1", findViewById<ImageButton>(R.id.c2x1))
        btnList.put("2x2", findViewById<ImageButton>(R.id.c2x2))
        btnList.put("2x3", findViewById<ImageButton>(R.id.c2x3))
        btnList.put("3x1", findViewById<ImageButton>(R.id.c3x1))
        btnList.put("3x2", findViewById<ImageButton>(R.id.c3x2))
        btnList.put("3x3", findViewById<ImageButton>(R.id.c3x3))

        for((loc, imgBtn) in btnList)
        {
            imgBtn.setOnClickListener {
                print(it)
            }
        }
    }

    private fun cellAction(game : Game, it : View, loc : String)
    {
        game.mark(loc)
    }

    private fun checkConditions(game : Game, loc : String)
    {
        if(game.checkWinningCondition(game.getTic(loc)))
        {
            Toast.makeText(this@MainActivity,
                game.getSign(loc)+" wins!!!",
                Toast.LENGTH_SHORT).show()

            // Reset and print grid
            game.start()
            applyCellsIMG(game)
        }
        else if(game.checkDeadLockCondition())
        {
            Toast.makeText(this@MainActivity,
                "All cells filled restarting...",
                Toast.LENGTH_SHORT).show()

            // Reset and print grid
            game.start()
            applyCellsIMG(game)
        }

        val sign = if(game.curSign!=0) if(game.curSign==1) "O" else "X" else " "
        findViewById<TextView>(R.id.currSign).setText(sign);
    }

    private fun applyCellsIMG(game : Game)
    {
        for((loc, imgBtn) in btnList)
        {
            applyCellImage(game, imgBtn, loc)
        }
    }

    private fun applyCellImage(game : Game, it : View, loc : String)
    {
        if(it is ImageButton)
        {
            if(game.getTic(loc)==1)
                it.setImageResource(R.drawable.im_circle)
            else if(game.getTic(loc)==-1)
                it.setImageResource(R.drawable.im_cross)
            else
                it.setImageBitmap(null);
        }
    }
}