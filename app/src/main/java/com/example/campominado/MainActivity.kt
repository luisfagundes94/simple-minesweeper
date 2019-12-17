package com.example.campominado

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_item.view.*

class MainActivity : AppCompatActivity() {

    private var score = 0
    private var randomItemList = GameUtil.createRandomItemList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        game_board.setOnItemClickListener { parent, view, position, id ->
            val item = view.img_row_item

            checkItemHiddenValue(position, item, randomItemList)
        }

        btn_try_again.setOnClickListener {
            restartGame()
        }
    }

    private fun init() {
        setTextMessage(textView = txt_score, text = "Score 0/55")
        disableButton(btn_try_again)
        disableTextView(txt_result)
        setGameBoardConfig(game_board, randomItemList)
    }

    private fun disableButton(button: Button) {
        button.isEnabled = false
    }

    private fun enableButton(button: Button) {
        button.isEnabled = true
    }

    private fun disableTextView(text: TextView) {
        text.visibility = View.GONE
    }

    private fun enableTextView(text: TextView) {
        text.visibility = View.VISIBLE
    }

    private fun setTextMessage(textView: TextView, text: String) {
        textView.text = text
    }

    private fun playFailSound() {
        val failSound = MediaPlayer.create(this, R.raw.fail)
        failSound.start()
    }

    private fun checkItemHiddenValue(position: Int, item: ImageView, randomList: List<Int>) {
        if (randomList[position] == R.drawable.facing_down) {
            score++
            setTextMessage(textView = txt_score, text = "Score $score/55")
            item.setImageResource(R.drawable.one)
        } else {
            playFailSound()
            item.setImageResource(R.drawable.bomb)
            enableButton(btn_try_again)
            enableTextView(txt_result)
            setTextMessage(textView = txt_result, text = "Ops, perdeu!")
            game_board.isEnabled = false
        }
    }

    private fun setGameBoardConfig(gameBoard: GridView, randomList: List<Int>) {
        val gameBoardAdapter = GameBoardAdapter(this.applicationContext, randomList)
        gameBoard.adapter = gameBoardAdapter
    }

    private fun restartGame() {
        restartRandomList()
        updateUi()
    }

    private fun restartRandomList() {
        randomItemList = GameUtil.createRandomItemList()
        setGameBoardConfig(game_board, randomItemList)
    }

    private fun updateUi() {
        disableButton(btn_try_again)
        score = 0
        game_board.isEnabled = true
        setTextMessage(text = "", textView = txt_result)
        setTextMessage(text = "Score 0/55", textView = txt_score)
    }

}
