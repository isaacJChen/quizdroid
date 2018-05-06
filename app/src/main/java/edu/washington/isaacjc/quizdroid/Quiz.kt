package edu.washington.isaacjc.quizdroid

class Quiz(text:String, answers: Array<String>, answer:Int) {
    private val text = text
    private val answers = answers
    private val answer = answer

    fun getQuestion(): String{
        return this.text
    }

    fun getOptions() : Array<String> {
        return  this.answers
    }

    fun getCorrectIndex() : Int {
        return this.answer
    }

}