package edu.washington.isaacjc.quizdroid

import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class TopicRepositoryJson: TopicRepository{
    var mdata: Array<Topic> = arrayOf()
    override fun getTopics(): Array<Topic> {
        fetchUrl()
        return mdata
    }

    fun fetchUrl(){

        val request = Request.Builder().url(QuizApp.NewQuizApp().getUrl()).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                println(body)

                val gson = GsonBuilder().create()
                println(gson)

                val data = gson.fromJson(body, Array<Topic>::class.java)
                Thread.sleep(3_000)
                QuizApp.NewQuizApp().setData(data)
                mdata = data
            }


            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute json request")
            }

        })
    }


}