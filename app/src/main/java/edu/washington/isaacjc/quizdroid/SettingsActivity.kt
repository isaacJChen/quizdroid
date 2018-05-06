package edu.washington.isaacjc.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.URLUtil
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class SettingsActivity : AppCompatActivity() {
    var warning = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val button = findViewById<Button>(R.id.refreshSave)
        val intervalText = findViewById<TextView>(R.id.refreshInterval)
        val urlTextField = findViewById<TextView>(R.id.urlToPull)
        intervalText.text = QuizApp.NewQuizApp().getInterval().toString()
        urlTextField.text = QuizApp.NewQuizApp().getUrl()

        button.setOnClickListener{
            fetchJson(urlTextField.text.toString())
            Thread.sleep(3_000)
            if (warning == "") {
                QuizApp.NewQuizApp().setInterval(intervalText.text.toString().toInt())
                QuizApp.NewQuizApp().setUrl(urlTextField.text.toString())
                val next = Intent(this, MainActivity::class.java)
                this.startActivity(next)
            } else {
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(applicationContext, warning, duration)
                toast.show()
            }

        }
    }


   fun fetchJson(url: String){
       val valid = URLUtil.isValidUrl(url)
       if (valid) {
           val request = Request.Builder().url(url).build()

           val client = OkHttpClient()
           client.newCall(request).enqueue(object: Callback {
               override fun onResponse(call: Call?, response: Response?) {
                   val res = response?.body()?.string()
                   if (res == null || (res[0] != '{' && res[0] != '[')) {
                       warning = "this html is not working, make sure you have internet and a valid url"
                   } else {
                       warning = ""
                   }

               }
               override fun onFailure(call: Call?, e: IOException?) {
                   warning = "this html is not working, make sure you have internet and a valid url"
               }

           })
       } else {
           warning = "this html is not working, make sure you have internet and a valid url"
       }

    }
}
