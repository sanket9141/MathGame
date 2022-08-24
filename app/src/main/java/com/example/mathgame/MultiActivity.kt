package com.example.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add.*
import java.util.*
import kotlin.random.Random

class MultiActivity : AppCompatActivity() {

    lateinit var textScore : TextView
    lateinit var textLife : TextView
    lateinit var textTime : TextView

    lateinit var textQuestion : TextView
    lateinit var editTextAnswer : EditText

    lateinit var buttonOk : Button
    lateinit var buttonNext : Button

    var correctAnswer = 0
    var userScore = 0
    var userLife = 3


    lateinit var timer : CountDownTimer     //countdowntimer is abstract class
    private val startTimerInMillis:Long = 30000
    var timeLeftInMillis : Long = startTimerInMillis



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi)

        supportActionBar!!.title = "Multiplication"

        textScore = findViewById(R.id.textViewScore)
        textLife = findViewById(R.id.textViewLife)
        textTime = findViewById(R.id.textViewTime)

        textQuestion = findViewById(R.id.textViewQues)

        editTextAnswer = findViewById(R.id.AnswerHere)

        buttonOk = findViewById(R.id.buttonOK)
        buttonNext = findViewById(R.id.buttonNext)

        gameContinue()


        buttonOk.setOnClickListener {

            val input = editTextAnswer.text.toString()   //checked the user input and converted to String

            if (input == "")
            {
                Toast.makeText(applicationContext,"Please enter the answer OR click NEXT", Toast.LENGTH_SHORT).show()
            }
            else{
                pauseTimer()

                val userAnswer = input.toInt()     //input again converted to int

                if (userAnswer == correctAnswer){

                    userScore = userScore + 10

                    textQuestion.text = "Congratulations...."

                    textViewScore.text = userScore.toString()


                }
                else{
                    userLife--

                    textQuestion.text = "Wrong Answer !!!"
                    textLife.text = userLife.toString()

                }
            }

        }

        buttonNext.setOnClickListener{

            pauseTimer()
            resetTimer()
            gameContinue()
            editTextAnswer.setText("")

            if(userLife == 0){
                Toast.makeText(applicationContext,"Game Over !!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MultiActivity,ResultActivity::class.java)
                intent.putExtra("score",userScore)
                startActivity(intent)
                finish()

            }
            else{
                gameContinue()

            }

        }
    }

    fun gameContinue()
    {
        val number1 = Random.nextInt(0,30)     //will generate random nos
        val number2 = Random.nextInt(0,30)

        textQuestion.text = "$number1 * $number2"

        correctAnswer = number1 * number2

        startTimer()
    }

    fun startTimer() {
        timer = object : CountDownTimer(timeLeftInMillis, 1000)  //object refers to anonymous class
        {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateText()

            }

            override fun onFinish() {

                pauseTimer()
                resetTimer()
                updateText()

                userLife--
                textLife.text = userLife.toString()
                textQuestion.text = "Sorry, Time is Up.."
            }

        }.start()
    }

    fun updateText(){

        val remainingTime:Int = (timeLeftInMillis/1000).toInt()
        textTime.text = String.format(Locale.getDefault(),"%02d",remainingTime)
    }
    fun pauseTimer(){
        timer.cancel()

    }
    fun resetTimer(){
        timeLeftInMillis = startTimerInMillis
        updateText()

    }
}
