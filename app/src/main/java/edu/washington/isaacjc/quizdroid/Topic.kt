package edu.washington.isaacjc.quizdroid

class Topic(title:String, shortDescription:String, longDescription:String, questions :Array<Quiz>) {
    private val title = title
    private val shortDescription = shortDescription
    private val longDescription = longDescription
    private val questions = questions

    fun getTitle():String{
        return this.title
    }

    fun getShortDescription():String {
        return this.shortDescription
    }

    fun getLongDescription():String {
        return this.longDescription
    }

    fun getQuesions() : Array<Quiz> {
        return this.questions
    }
}