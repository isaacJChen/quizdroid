package edu.washington.isaacjc.quizdroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import android.content.Intent
import android.os.Parcelable
import android.widget.Button
import kotlinx.android.synthetic.main.category_main.*
import java.io.Serializable


class TopicOverViewActivity : AppCompatActivity() {

    val manager = supportFragmentManager
    val options = arrayOf("wrong answer 1", "wrong answer 2", "correct answer", "wrong answer 3", "correct answer","wrong answer","another wrong answer","yet another wrong one")
    val questions = arrayOf("This is the first question. What is the correct answer?", "This is the second question. What is the correct answer?")
    val correctAnswers = intArrayOf(2, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_over_view)
        showFragmentTopOverView()

    }

    fun showFragmentTopOverView() {
        var bundle = Bundle()
        val i = intent
        val category = i.getStringExtra("category")
        val description = i.getStringExtra("description")
        bundle.putString("category",category)
        bundle.putString("description",description)
        bundle.putStringArray("options",options)
        bundle.putInt("size", questions.size)
        bundle.putIntArray("correctAnswers", correctAnswers)

        bundle.putStringArray("questions", questions)
        val transaction = manager.beginTransaction()
        val fragment = TopicOverViewFragment()
        fragment.arguments = bundle
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
