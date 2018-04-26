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




        val chosenAnswer = arguments!!.getInt("chosenAnswer", 0)
        var current = arguments!!.getInt("current", 0)
        val answers = arguments!!.getIntArray("correctAnswers")
        val options = arguments!!.getStringArray("options")
        val questions = arguments!!.getStringArray("questions")
        val size = arguments!!.getInt("size")
        var currentScore = arguments!!.getInt("currentScore", 0)

        val shiftingIndex = current*4


        if (options[chosenAnswer+shiftingIndex] == options[shiftingIndex+answers[current]] as String) {
            currentScore+=1
        }

        view.findViewById<TextView>(R.id.chosenAnswerTextView).text = options[chosenAnswer+shiftingIndex] as String
        view.findViewById<TextView>(R.id.correctAnswerTextView).text = options[shiftingIndex+answers[current]] as String
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
                bundle.putInt("size", arguments!!.getInt("size"))
                bundle.putInt("current", current)
                bundle.putInt("currentScore", currentScore)
                bundle.putIntArray("correctAnswers", arguments!!.getIntArray("correctAnswers"))
                bundle.putStringArray("options", arguments!!.getStringArray("options"))
                bundle.putStringArray("questions", arguments!!.getStringArray("questions"))


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
