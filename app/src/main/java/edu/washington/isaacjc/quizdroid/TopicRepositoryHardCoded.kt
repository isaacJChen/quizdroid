package edu.washington.isaacjc.quizdroid

class TopicRepositoryHardCoded:TopicRepository {
    override fun getTopics():Array<Topic> {
        //return this.topics
        var q1Math = Quiz("What is 1+1?", arrayOf("1","2","3","4"), 1)
        var q2Math = Quiz("What is square root of -1?", arrayOf("i","π","-1","e"), 0)
        var math = Topic("Math", "math is hard", "math is hard but you can do it!", arrayOf(q1Math, q2Math))

        var q1Physics = Quiz("What is gravitational acceleration?", arrayOf("9.8 m/s²","3.14 m/s²","4.20 m/s²","2.5 m/s²"), 0)
        var q2Physics = Quiz("What is the speed of light?", arrayOf("299792458 m/s", "1 m/s", "5604 m/h", "speed of light is not a constant"), 0)
        var physics = Topic("Physics", "Become the next Einstein", "Physics is fun!", arrayOf(q1Physics, q2Physics))

        var q1Marvel = Quiz("Who is not part of the Avengers?", arrayOf("Tony Stark", "Steven Roger", "Bruce Wayne", "Bruce Banner"), 2)
        var q2Marvel = Quiz("Who is Thor's father?", arrayOf("Odin", "Iron Man", "Loki", "Door"), 0)
        var marvel = Topic("Marvel", "Who is excited for Infinity War!!", "MCU is better than the comics. Yup I said it.", arrayOf(q1Marvel, q2Marvel))

        return arrayOf(math, physics, marvel)
    }


}