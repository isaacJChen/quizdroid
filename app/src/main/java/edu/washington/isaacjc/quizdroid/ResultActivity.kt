package edu.washington.isaacjc.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val i = intent
        val chosenAnswer = i.getIntExtra("chosenAnswer", 0) //no need to pass on
        var current = i.getIntExtra("currentQuestionNumber", 0)
        val answers = i.getIntArrayExtra("correctAnswers")
        val options = i.getSerializableExtra("options") as Array<*>
        val innerArray = options[current] as Array<*>
        val questions = i.getStringArrayExtra("questions")
        val totalNumberOfQuestions = i.getIntExtra("totalNumberOfQuestions", 0)
        var currentScore = i.getIntExtra("currentScore", 0)


        if (innerArray[chosenAnswer] as String == innerArray[answers[current]] as String) {
            currentScore+=1
        }

        findViewById<TextView>(R.id.chosenAnswerTextView).text = innerArray[chosenAnswer] as String
        findViewById<TextView>(R.id.correctAnswerTextView).text = innerArray[answers[current]] as String
        findViewById<TextView>(R.id.playerStatTextView).text = String.format("You have %d out of %d correct!", currentScore, totalNumberOfQuestions)

        current+=1
        val button = findViewById<Button>(R.id.nextButton)
        button.setOnClickListener {
            val next = Intent(this, MainActivity::class.java)
            startActivity(next)
        }

        if (totalNumberOfQuestions == current) {
            button.text = "FINISH"
            button.setOnClickListener {
                val next = Intent(this, MainActivity::class.java)
                startActivity(next)
            }
        } else {
            button.text = "NEXT"
            button.setOnClickListener{
                val next = Intent(this, QuestionPageActivity::class.java)
                next.putExtra("currentQuestionNumber", current)
                next.putExtra("correctAnswers", answers)
                next.putExtra("options", options)
                next.putExtra("questions", questions)
                next.putExtra("totalNumberOfQuestions", totalNumberOfQuestions)
                next.putExtra("currentScore", currentScore)
                startActivity(next)
            }
        }




    }
}
