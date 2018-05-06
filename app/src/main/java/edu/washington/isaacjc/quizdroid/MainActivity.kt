package edu.washington.isaacjc.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //val listView = findViewById<ListView>(R.id.main_listview)
        fetchJson(QuizApp.NewQuizApp().getUrl())
        //listView.adapter = CustomAdaptor(this, categories, description)
        val settings = findViewById<Button>(R.id.settingsButton)
        settings.setOnClickListener {
            val next = Intent(this, SettingsActivity::class.java)

            this.startActivity(next)
        }

    }

    fun fetchJson(url: String){
        var urlToUse:String
        if (url == "") {
            urlToUse = "http://tednewardsandbox.site44.com/questions.json"
        } else {
            urlToUse = url
        }

        val request = Request.Builder().url(urlToUse).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                println(body)

                val gson = GsonBuilder().create()
                println(gson)

                val data = gson.fromJson(body, Array<Topic>::class.java)
                QuizApp.NewQuizApp().setData(data)

                runOnUiThread {
                    main_listview.adapter = CustomAdaptor(data)
                }
            }


            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute json request")
            }

        })
    }

    private class CustomAdaptor(data: Array<Topic>): BaseAdapter() {
        private val mCategories: Array<String>
        private val mDescription: Array<String>
        private val categories = Array(data.size) {""}
        private val description = Array(data.size) {""}

        init {
            //write a for loop to get all data out of the object and put into the field

            for ((index, value) in data.withIndex()) {
                categories[index] = value.getTitle()
                description[index] = value.getShortDescription()
            }
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
            val layoutInflater = LayoutInflater.from(parent?.context)
            val row = layoutInflater.inflate(R.layout.category_main, parent, false)
            val title = row.findViewById<TextView>(R.id.category_textView)
            title.text = mCategories[position]
            row.setOnClickListener {
                val next = Intent(parent?.context, TopicOverViewActivity::class.java)
                next.putExtra("category", position)

                parent?.context?.startActivity(next)
            }
            return row
        }

    }
}
