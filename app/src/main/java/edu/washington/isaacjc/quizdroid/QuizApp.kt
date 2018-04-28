package edu.washington.isaacjc.quizdroid

import android.app.Application
import android.util.Log

class QuizApp : Application() {
    private val tag = "QuizApp"
    private val topicRepo = TopicRepositoryHardCoded()

    override fun onCreate() {
        super.onCreate()
        Log.i(tag, "QuizApp onCreate called!")
    }

    fun getTopicRepo():TopicRepositoryHardCoded{
        return topicRepo
    }

    companion object {
        val quizApp = QuizApp()
        fun NewQuizApp():QuizApp{
            return quizApp
        }
    }
}