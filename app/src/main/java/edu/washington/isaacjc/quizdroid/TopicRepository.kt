package edu.washington.isaacjc.quizdroid

interface TopicRepository {
    fun getTopics():Array<Topic>
}