package edu.washington.isaacjc.quizdroid

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class TopicOverViewFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        // Inflate the layout for this fragment
        Log.i("Fragment", "ONCREATEVIEWISCALLED!!!")
        val topicRepo = QuizApp.NewQuizApp().getData()
        val view = inflater.inflate(R.layout.fragment_topic_over_view, container, false)
        val title = view.findViewById<TextView>(R.id.topicOverViewTitleTextView)
        val description = view.findViewById<TextView>(R.id.descriptionTextView)
        val numberOfQuestions = view.findViewById<TextView>(R.id.numberOfQuestionsTextView)
        val beginButton = view.findViewById<Button>(R.id.beginButton)

        title.text = topicRepo[arguments!!.getInt("category")].getTitle()
        description.text = topicRepo[arguments!!.getInt("category")].getShortDescription()
        val size = topicRepo[arguments!!.getInt("category")].getQuesions().size
        numberOfQuestions.text = String.format("number of questions: %d", size)

        beginButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("category",arguments!!.getInt("category"))
            bundle.putInt("current", 0)

            val fragment = QuestionFragment()
            fragment.arguments = bundle
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        Log.i("Fragment", "on start is called")
    }

}
