package edu.washington.isaacjc.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView

class QuestionPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_page)

        val i = intent
        val current = i.getIntExtra("currentQuestionNumber", 0)
        findViewById<TextView>(R.id.questionTextView).text = i.getStringArrayExtra("questions")[current]
        val button = findViewById<Button>(R.id.submitButton)


        val options = i.getSerializableExtra("options") as Array<*>
        val innerArray = options[current] as Array<*>

        val option1 = findViewById<RadioButton>(R.id.radioButton)
        option1.text = innerArray[0] as String
        option1.setOnClickListener {
            button.isEnabled = true
        }
        val option2 = findViewById<RadioButton>(R.id.radioButton2)
        option2.text = innerArray[1] as String
        option2.setOnClickListener {
            button.isEnabled = true
        }
        val option3 = findViewById<RadioButton>(R.id.radioButton3)
        option3.text = innerArray[2] as String
        option3.setOnClickListener {
            button.isEnabled = true
        }
        val option4 = findViewById<RadioButton>(R.id.radioButton4)
        option4.text = innerArray[3] as String
        option4.setOnClickListener {
            button.isEnabled = true
        }

        button.setOnClickListener {
            val next = Intent(this, ResultActivity::class.java)
            startActivity(next)
        }




    }
}
