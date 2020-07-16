package com.example.rosledproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity

import android.content.Intent
import android.os.*
import android.util.Log
import android.view.View
import android.widget.*
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.lang.Exception
import java.net.Socket
import java.util.*

class MainActivity : Activity() {

    private lateinit var devicePanel: LinearLayout
    private lateinit var deviceListView: ListView

    private lateinit var progressBar: ProgressBar

    private lateinit var ipText : EditText
    private lateinit var connectButton: Button



    private var backPressedNumber  = 0


    companion object {
        lateinit var socket: Socket
    }


    private fun initialize() {
        initControl()
        initData()
    }

    private fun initControl() {

        //deviceListView = findViewById(R.id.MA_DEVICES)
        progressBar = findViewById(R.id.MA_CONNECTION_PROGRESS)
        ipText = findViewById(R.id.MA_IP)
        connectButton = findViewById(R.id.MA_CONNECT)

    }

    private fun initData() {

        connectButton.setOnClickListener {
            ConnectionTask().execute(ipText.text.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
    }


    override fun onBackPressed() {
        if(backPressedNumber >= 1)
            finish()

        Toast.makeText(this, "Çıkmak için geri tuşuna bir kez daha basınız.", Toast.LENGTH_SHORT).show()
        backPressedNumber++

        Thread(Runnable {
            Thread.sleep(4000)
            backPressedNumber = 0
        }).start()
    }



    inner class ConnectionTask : AsyncTask<String, Int, Pair<Boolean, String>>(){

        override fun onPreExecute() {
            super.onPreExecute()
            progressBar.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg p0: String?): Pair<Boolean, String> {
            var info  = ""


            try {

                publishProgress(20)

                socket =  Socket(p0[0], 65432)

                publishProgress(40)


                publishProgress(80)

                /*
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                info += "\n ${socket.connectionType}"
            }
            */

                //m_is = socket.inputStream
                //m_os = socket.outputStream

            }catch (ex : IOException){
                return Pair(false, ex.localizedMessage)
            }

            publishProgress(99)

            return Pair(true, info)
        }


        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)

            progressBar.progress = values[0]!!
        }

        override fun onPostExecute(result: Pair<Boolean, String>?) {
            super.onPostExecute(result)

            if(result!!.first) {
                //deviceInfo.text = result.second

                var intent = Intent(this@MainActivity , ConnectedActivity::class.java)
                intent.putExtra("INFO", result.second)
                startActivity(intent)

                Toast.makeText(this@MainActivity, "Connection Completed.", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this@MainActivity, "Error on connection..." + result!!.second, Toast.LENGTH_SHORT).show()
            }

            progressBar.progress = 0
            progressBar.visibility = View.GONE
        }
    }


}

