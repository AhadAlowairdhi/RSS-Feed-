package com.example.rssfeedapp

import android.app.ProgressDialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    lateinit var rvQuestion: RecyclerView
    lateinit var question: MutableList<Question>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvQuestion = findViewById(R.id.rvMain)
        FetchQuestions().execute()
    }

    private inner class FetchQuestions : AsyncTask<Void, Void, MutableList<Question>>() {
        val parser = XmlParser()
        val progressD = ProgressDialog(this@MainActivity)

        override fun onPreExecute() {
            super.onPreExecute()
            progressD.setMessage("Please Wait")
            progressD.show()
        }

        override fun doInBackground(vararg params: Void?): MutableList<Question> {
            val url = URL("https://stackoverflow.com/feeds")
            val urlConnection = url.openConnection() as HttpURLConnection
            question =
                urlConnection.inputStream?.let { parser.parse(it) } as MutableList<Question>
            return question
        }

        override fun onPostExecute(result: MutableList<Question>?) {
            super.onPostExecute(result)
            progressD.dismiss()
            rvQuestion.adapter = myAdapter(result)
            rvQuestion.layoutManager = LinearLayoutManager(applicationContext)
        }
    }
}