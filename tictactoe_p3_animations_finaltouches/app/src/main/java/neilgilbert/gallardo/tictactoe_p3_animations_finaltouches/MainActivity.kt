package neilgilbert.gallardo.tictactoe_p3_animations_finaltouches

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import neilgilbert.gallardo.tictactoe_p3_animations_finaltouches.game.Game

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
                cellAction(tttGame, it, loc)
                applyCellsIMG(tttGame)
                checkConditions(tttGame, loc)
            }
        }
    }

    private fun cellAction(game : Game, it : View, loc : String)
    {
        if(game.mark(loc))
        {
            val cellAnim = AnimationUtils.loadAnimation(this, R.anim.cell_tic_appear)
            it.startAnimation(cellAnim)
            playStatusAnim(game)
        }
    }

    private fun checkConditions(game : Game, loc : String)
    {
        if(game.checkWinningCondition(game.getTic(loc)))
        {
            onGameEndMessage("WIN!",game.getSign(loc)+" wins!!!", game)
        }
        else if(game.checkDeadLockCondition())
        {
            onGameEndMessage("DRAW","All cells filled, draw, restarting...", game)
        }
    }

    private fun onGameEndMessage(title: String, message: String, game: Game)
    {
        var builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Restart game..."){ dialog, id ->
            game.start()
            applyCellsIMG(game)
            playStatusAnim(game)
        }
        builder.show()
    }

    private fun playStatusAnim(game: Game)
    {
        val status_bgCross = findViewById<ImageView>(R.id.status_bgCross)
        val status_bgCircle = findViewById<ImageView>(R.id.status_bgCircle)

        if(game.curSign==-1)
        {
            val statusAnim = AnimationUtils.loadAnimation(this, R.anim.active_top_bot)
            statusAnim.setAnimationListener(object : Animation.AnimationListener {
                // All the other override functions
                override fun onAnimationStart(p0: Animation?) {
                    status_bgCircle.setBackgroundColor(0xE60A0A0A.toInt())
                }

                override fun onAnimationRepeat(p0: Animation?) {

                }

                override fun onAnimationEnd(p0: Animation?) {
                    status_bgCircle.setBackgroundColor(0x00000000.toInt())
                    status_bgCross.setBackgroundColor(0xE60A0A0A.toInt())
                }
            })
            status_bgCircle.startAnimation(statusAnim)
        }
        else if(game.curSign==1){
            val statusAnim = AnimationUtils.loadAnimation(this, R.anim.active_bot_top)
            statusAnim.setAnimationListener(object : Animation.AnimationListener {
                // All the other override functions
                override fun onAnimationStart(p0: Animation?) {
                    status_bgCross.setBackgroundColor(0xE60A0A0A.toInt())
                }

                override fun onAnimationRepeat(p0: Animation?) {

                }

                override fun onAnimationEnd(p0: Animation?) {
                    status_bgCross.setBackgroundColor(0x00000000.toInt())
                    status_bgCircle.setBackgroundColor(0xE60A0A0A.toInt())
                }
            })
            status_bgCross.startAnimation(statusAnim)
        }
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