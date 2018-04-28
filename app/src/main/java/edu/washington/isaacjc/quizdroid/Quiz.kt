package edu.washington.isaacjc.quizdroid

class Quiz(question:String, options: Array<String>, correctIndex:Int) {
    private val quesiton = question
    private val options = options
    private val correctIndex = correctIndex

    fun getQuestion(): String{
        return this.quesiton
    }

    fun getOptions() : Array<String> {
        return  this.options
    }

    fun getCorrectIndex() : Int {
        return this.correctIndex
    }

}