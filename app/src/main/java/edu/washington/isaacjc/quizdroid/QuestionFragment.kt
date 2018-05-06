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
import android.widget.RadioButton
import android.widget.TextView


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [QuestionFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [QuestionFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class QuestionFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_question, container, false)
        val topicRepo = QuizApp.NewQuizApp().getData()

        val current = arguments!!.getInt("current", 0)
        var chosenAnswer = 0

        //this is a list of questions
        val question = topicRepo[arguments!!.getInt("category")].getQuesions()[current]
        val currentScore = arguments!!.getInt("currentScore", 0)

        view.findViewById<TextView>(R.id.questionTextView).text = question.getQuestion()
        val button = view.findViewById<Button>(R.id.submitButton)


        val options = question.getOptions()

        val option1 = view.findViewById<RadioButton>(R.id.radioButton)

        option1.text = options[0]
        option1.setOnClickListener {
            button.isEnabled = true
            chosenAnswer = 0
        }
        val option2 = view.findViewById<RadioButton>(R.id.radioButton2)
        option2.text = options[1]
        option2.setOnClickListener {
            button.isEnabled = true
            chosenAnswer = 1
        }
        val option3 = view.findViewById<RadioButton>(R.id.radioButton3)
        option3.text = options[2]
        option3.setOnClickListener {
            button.isEnabled = true
            chosenAnswer = 2
        }
        val option4 = view.findViewById<RadioButton>(R.id.radioButton4)
        option4.text = options[3]
        option4.setOnClickListener {
            button.isEnabled = true
            chosenAnswer = 3
        }

        button.setOnClickListener {
            val bundle = Bundle()

            bundle.putInt("current", current)
            bundle.putInt("currentScore", currentScore)
            bundle.putInt("chosenAnswer", chosenAnswer)
            bundle.putInt("category", arguments!!.getInt("category"))


            val fragment = ResultFragment()
            fragment.arguments = bundle
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        return view
    }




}
