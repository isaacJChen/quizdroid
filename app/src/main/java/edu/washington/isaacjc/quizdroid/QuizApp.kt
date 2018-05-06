package edu.washington.isaacjc.quizdroid

import android.app.Application
import android.util.Log

class QuizApp : Application() {
    private val tag = "QuizApp"
    private var topicRepo = TopicRepositoryJson()
    private var data:Array<Topic> = arrayOf()
    private var url = "http://tednewardsandbox.site44.com/questions.json"
    private var interval = 10

    override fun onCreate() {
        super.onCreate()
        Log.i(tag, "QuizApp onCreate called!")
    }

    fun getTopicRepoJson():TopicRepositoryJson {
        return topicRepo
    }

    companion object {
        val quizApp = QuizApp()
        fun NewQuizApp():QuizApp{
            return quizApp
        }
    }

    fun setData(newData: Array<Topic>) {
        this.data = newData
    }

    fun getData():Array<Topic> {
        return this.data
    }

    fun getUrl(): String{
        return this.url
    }

    fun setUrl(s: String) {
        this.url = s
    }

    fun getInterval(): Int{
        return this.interval
    }

    fun setInterval(i: Int) {
        this.interval = i
    }

}