package edu.washington.isaacjc.quizdroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import android.content.Intent
import android.os.Parcelable
import android.widget.Button
import kotlinx.android.synthetic.main.category_main.*
import java.io.Serializable


class TopicOverViewActivity : AppCompatActivity() {

    val manager = supportFragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_over_view)
        showFragmentTopOverView()

    }

    fun showFragmentTopOverView() {
        var bundle = Bundle()
        val i = intent
        bundle.putInt("category",i.getIntExtra("category", 0))
        val transaction = manager.beginTransaction()
        val fragment = TopicOverViewFragment()
        fragment.arguments = bundle
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
