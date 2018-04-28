package edu.washington.isaacjc.quizdroid

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ResultFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ResultFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_result, container, false)
        val topicRepo = QuizApp.NewQuizApp().getTopicRepo().getTopics()

        val chosenAnswer = arguments!!.getInt("chosenAnswer", 0)
        var current = arguments!!.getInt("current", 0)
        val question = topicRepo[arguments!!.getInt("category")].getQuesions()[current]
        val options = question.getOptions()

        val size = topicRepo[arguments!!.getInt("category")].getQuesions().size
        var currentScore = arguments!!.getInt("currentScore", 0)


        if (question.getCorrectIndex() == chosenAnswer) {
            currentScore+=1
        }

        view.findViewById<TextView>(R.id.chosenAnswerTextView).text = options[chosenAnswer]
        view.findViewById<TextView>(R.id.correctAnswerTextView).text = options[question.getCorrectIndex()]
        view.findViewById<TextView>(R.id.playerStatTextView).text = String.format("You have %d out of %d correct!", currentScore, size)

        current+=1
        val button = view.findViewById<Button>(R.id.nextButton)

        if (size == current) {
            button.text = "FINISH"
            button.setOnClickListener {
                val next = Intent(activity, MainActivity::class.java)
                startActivity(next)
            }
        } else {
            button.text = "NEXT"
            button.setOnClickListener{
                val bundle = Bundle()
                bundle.putInt("current", current)
                bundle.putInt("currentScore", currentScore)
                bundle.putInt("category", arguments!!.getInt("category"))

                val fragment = QuestionFragment()
                fragment.arguments = bundle
                val fragmentManager = activity!!.supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.container, fragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
        }




        return view
    }

}
