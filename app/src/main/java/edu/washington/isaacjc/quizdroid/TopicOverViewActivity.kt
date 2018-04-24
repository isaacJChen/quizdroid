package edu.washington.isaacjc.quizdroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import android.content.Intent
import android.widget.Button
import java.io.Serializable


class TopicOverViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_over_view)

        val options = arrayOf(arrayOf("wrong answer 1", "wrong answer 2", "correct answer", "wrong answer 3"), arrayOf("correct answer","wrong answer","another wrong answer","yet another wrong one"))
        val questions = arrayOf("This is the first question. What is the correct answer?", "This is the second question. What is the correct answer?")
        val title = findViewById<TextView>(R.id.topicOverViewTitleTextView)
        val description = findViewById<TextView>(R.id.descriptionTextView)
        val numberOfQuestions = findViewById<TextView>(R.id.numberOfQuestionsTextView)
        val beginButton = findViewById<Button>(R.id.beginButton)

        val i = intent
        title.text = i.getStringExtra("category")
        description.text = i.getStringExtra("description")
        numberOfQuestions.text = String.format("number of questions: %d", questions.size)

        beginButton.setOnClickListener {
            val next = Intent(this, QuestionPageActivity::class.java)
            next.putExtra("questions", questions)
            next.putExtra("options", options)
            next.putExtra("currentQuestionNumber", 0)
            next.putExtra("totalNumberOfQuestions", questions.size)
            startActivity(next)
        }
    }
}
