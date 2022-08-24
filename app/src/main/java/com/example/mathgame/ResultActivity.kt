package com.example.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class ResultActivity : AppCompatActivity() {

    lateinit var result : TextView
    lateinit var exit : Button
    lateinit var playAgain : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        result = findViewById(R.id.textViewResult)
        exit = findViewById(R.id.buttonExit)
        playAgain = findViewById(R.id.buttonPlayAgain)

        val score = intent.getIntExtra("score",0)
        result.text = "Your Score is :" +score

        playAgain.setOnClickListener {
            val intent = Intent(this@ResultActivity,MainActivity::class.java)
            startActivity(intent)
            finish()    //when this intent opens another page, this method will close this page
        }
        exit.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}