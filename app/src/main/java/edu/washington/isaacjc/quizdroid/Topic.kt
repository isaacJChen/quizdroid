package edu.washington.isaacjc.quizdroid

class Topic(title:String, desc:String, longDescription:String, questions :Array<Quiz>) {
    private val title = title
    private val desc = desc
    private val longDescription = longDescription
    private val questions = questions

    fun getTitle():String{
        return this.title
    }

    fun getShortDescription():String {
        return this.desc
    }

    fun getLongDescription():String {
        return this.longDescription
    }

    fun getQuesions() : Array<Quiz> {
        return this.questions
    }
}