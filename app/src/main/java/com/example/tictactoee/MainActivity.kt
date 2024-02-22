package com.example.tictactoee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var board: Array<Array<Button>>
    lateinit var displayTv: TextView
    lateinit var resetBtn: Button

    var PLAYER = true
    var TURN_COUNT = 0

    var boardStatus = Array(3) { IntArray(3) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn1 = findViewById<Button>(R.id.btn1)
        var btn2 = findViewById<Button>(R.id.btn2)
        var btn3 = findViewById<Button>(R.id.btn3)
        var btn4 = findViewById<Button>(R.id.btn4)
        var btn5 = findViewById<Button>(R.id.btn5)
        var btn6 = findViewById<Button>(R.id.btn6)
        var btn7 = findViewById<Button>(R.id.btn7)
        var btn8 = findViewById<Button>(R.id.btn8)
        var btn9 = findViewById<Button>(R.id.btn9)

         displayTv = findViewById<TextView>(R.id.displayTv)
         resetBtn = findViewById<Button>(R.id.resetBtn)

        board = arrayOf(
            arrayOf(btn1, btn2, btn3),
            arrayOf(btn4, btn5, btn6),
            arrayOf(btn7, btn8, btn9)
        )

        for( i in board){
            for(button in i){
                button.setOnClickListener(this)
            }
        }

        initializeBoardStatus()

        resetBtn.setOnClickListener(){
            PLAYER = true
            TURN_COUNT = 0
            initializeBoardStatus()
          }

    }

//    private fun initializeBoardStatus() {
//        for(i in 0..2){
//            for (j in 0..2){
//
//                boardStatus[i][j] = -1
//                board[i][j].isEnabled = true
//                board[i][j].text = ""
//
//            }
//        }
//    }

    private fun initializeBoardStatus() {
        for (i in 0..2) {
            for (j in 0..2) {
                boardStatus[i][j] = -1
            }
        }

        for (i in board) {
            for (button in i){
                button.isEnabled = true
                 button.text = ""
          }
       }
    }



    override fun onClick(view: View?) {

        when(view?.id) {

            R.id.btn1 -> {
               updateValue(row = 0, col = 0, player = PLAYER)
            }

            R.id.btn2 -> {
                updateValue(row = 0, col = 1, player = PLAYER)
            }

            R.id.btn3 -> {
                updateValue(row = 0, col = 2, player = PLAYER)
            }

            R.id.btn4 -> {
                updateValue(row = 1, col = 0, player = PLAYER)
             }

            R.id.btn5 -> {
                updateValue(row = 1, col = 1, player = PLAYER)
             }

            R.id.btn6 -> {
                updateValue(row = 1, col = 2, player = PLAYER)
            }

            R.id.btn7 -> {
                updateValue(row = 2, col = 0, player = PLAYER)
            }

            R.id.btn8 -> {
                updateValue(row = 2, col = 1, player = PLAYER)
            }

            R.id.btn9 -> {
                updateValue(row = 2, col = 2, player = PLAYER)
            }

        }

        TURN_COUNT++
        PLAYER = !PLAYER

       if(PLAYER){
           updateDisplay("Player X Turn")
       } else{
            updateDisplay("Player O Turn")
       }

        if(TURN_COUNT == 9){
            updateDisplay("GAME DRAW")
        }
        checkWinner()
    }

    private fun checkWinner(){
        //Horizontal
        for (i in 0..2) {
            if (boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]) {
                if (boardStatus[i][0] == 1) {
                    updateDisplay("Player X Winner")
                    break
                } else if (boardStatus[i][0] == 0) {
                    updateDisplay("Player O Winner")
                    break
                }
            }
        }
        // Vertical
        for (i in 0..2) {
            if (boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]) {
                if (boardStatus[0][i] == 1) {
                    updateDisplay("Player X Winner")
                    break
                } else if (boardStatus[0][i] == 0) {
                    updateDisplay("Player O Winner")
                    break
                }
            }
        }
        //1st Diagonal
        if (boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]) {
            if (boardStatus[0][0] == 1) {
                updateDisplay("Player X Winner")
            } else if (boardStatus[0][0] == 0) {
                updateDisplay("Player O Winner")
            }
        }
        //Second Diagonal
        if (boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]) {
            if (boardStatus[0][2] == 1) {
                updateDisplay("Player X Winner")
            } else if (boardStatus[0][2] == 0) {
                updateDisplay("Player O Winner")
            }
        }

    }

    private fun updateDisplay(text: String) {
        displayTv.text = text
        if(text.contains("Winner")){
            disableButton()
        }
    }

    private fun disableButton() {
        for(i in board){
            for(button in i){
                button.isEnabled = false
            }
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text: String = if(player) "X" else "O"
         val value: Int = if(player) 1 else 0

          board[row][col].apply {
              isEnabled = false
              setText(text)
          }
        boardStatus[row][col] = value

    }

 }