package edu.washington.isaacjc.quizdroid

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    val topicRepo = QuizApp.NewQuizApp().getTopicRepo().getTopics()
    var categories:Array<String> = Array<String>(topicRepo.size){
        ""
    }
    val description:Array<String> = Array<String>(topicRepo.size){
        ""
    }

    init {
        for ((i, elem) in topicRepo.withIndex()) {
            categories[i] = elem.getTitle()
            description[i] = elem.getShortDescription()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.main_listview)
        listView.adapter = CustomAdaptor(this, categories, description)

    }

    private class CustomAdaptor(context: Context, categories: Array<String>, description: Array<String>): BaseAdapter() {
        private val mContext: Context
        private val mCategories: Array<String>
        private val mDescription: Array<String>

        init {
            mContext = context
            mCategories = categories
            mDescription = description
        }

        override fun getCount(): Int {
            return mCategories.size
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return "String"
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val row = layoutInflater.inflate(R.layout.category_main, parent, false)
            val title = row.findViewById<TextView>(R.id.category_textView)
            title.text = mCategories[position]
            row.setOnClickListener {
                val next = Intent(mContext, TopicOverViewActivity::class.java)
                next.putExtra("category", position)

                mContext.startActivity(next)
            }
            return row
        }

    }
}
