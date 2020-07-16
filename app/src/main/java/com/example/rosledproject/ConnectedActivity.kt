package com.example.rosledproject

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.*
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import kotlin.math.log

class ConnectedActivity : Activity() {


    private lateinit var forwardButton: ImageButton
    private lateinit var backwardButton: ImageButton
    private lateinit var leftButton: ImageButton
    private lateinit var rightButton: ImageButton
    private var buttons = intArrayOf(0, 0, 0, 0)
    private var speed = intArrayOf(255, 255)

    private lateinit var onTouchListener : View.OnTouchListener

    private var buttonEnable : Boolean = true
    private lateinit var lastButton : ImageButton



    private lateinit var m_os: OutputStream
    private lateinit var m_is: InputStream


    private fun initialize() {
        initControl()
        initData()
    }

    private fun initControl() {
        forwardButton = findViewById(R.id.CA_UP)
        backwardButton = findViewById(R.id.CA_DOWN)
        rightButton = findViewById(R.id.CA_RIGHT)
        leftButton = findViewById(R.id.CA_LEFT)


    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initData() {
        m_is = MainActivity.socket.inputStream
        m_os = MainActivity.socket.outputStream


        onTouchListener = object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {

                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {

                            lastButton = v as ImageButton

                            when (v as ImageButton) {

                                forwardButton -> {
                                    buttons[0] = 1
                                }
                                backwardButton -> {
                                    buttons[1] = 1
                                }
                                leftButton -> {
                                    buttons[2] = 1
                                }
                                rightButton -> {
                                    buttons[3] = 1
                                }
                            }

                            Log.i("BUTTON_STAT ", buttons.joinToString())
                            Log.i("BUTTON_STAT ", buttons.joinToString())


                        when(buttons.joinToString()){
                                ButtonStatus.FORWARD.intArray.joinToString()->{writeInfo(Movement.FORWARD.str)}
                                ButtonStatus.FORWARD_LEFT.intArray.joinToString()->{writeInfo(Movement.FORWARD_LEFT.str)}
                                ButtonStatus.FORWARD_RIGHT.intArray.joinToString()->{writeInfo(Movement.FORWARD_RIGHT.str)}
                                ButtonStatus.BACKWARD.intArray.joinToString()->{writeInfo(Movement.BACKWARD.str)}
                                ButtonStatus.BACKWARD_LEFT.intArray.joinToString()->{writeInfo(Movement.BACKWARD_LEFT.str)}
                                ButtonStatus.BACKWARD_RIGHT.intArray.joinToString()->{writeInfo(Movement.BACKWARD_RIGHT.str)}
                                ButtonStatus.RIGHT.intArray.joinToString()->{writeInfo(Movement.RIGHT.str)}
                                ButtonStatus.LEFT.intArray.joinToString()->{writeInfo(Movement.LEFT.str)}
                                ButtonStatus.STOP.intArray.joinToString()->{writeInfo(Movement.STOP.str)}
                                else -> Log.i("BUTTON_STAT ", "NOTHIIG")
                        }


                    }
                    MotionEvent.ACTION_UP -> {

                        when (v as ImageButton) {

                            forwardButton -> {
                                buttons[0] = 0
                            }
                            backwardButton -> {
                                buttons[1] = 0
                            }
                            leftButton -> {
                                buttons[2] = 0
                            }
                            rightButton -> {
                                buttons[3] = 0
                            }
                        }

                        when(buttons.joinToString()){
                            ButtonStatus.FORWARD.intArray.joinToString()->{writeInfo(Movement.FORWARD.str)}
                            ButtonStatus.FORWARD_LEFT.intArray.joinToString()->{writeInfo(Movement.FORWARD_LEFT.str)}
                            ButtonStatus.FORWARD_RIGHT.intArray.joinToString()->{writeInfo(Movement.FORWARD_RIGHT.str)}
                            ButtonStatus.BACKWARD.intArray.joinToString()->{writeInfo(Movement.BACKWARD.str)}
                            ButtonStatus.BACKWARD_LEFT.intArray.joinToString()->{writeInfo(Movement.BACKWARD_LEFT.str)}
                            ButtonStatus.BACKWARD_RIGHT.intArray.joinToString()->{writeInfo(Movement.BACKWARD_RIGHT.str)}
                            ButtonStatus.RIGHT.intArray.joinToString()->{writeInfo(Movement.RIGHT.str)}
                            ButtonStatus.LEFT.intArray.joinToString()->{writeInfo(Movement.LEFT.str)}
                            ButtonStatus.STOP.intArray.joinToString()->{writeInfo(Movement.STOP.str)}
                            else -> Log.i("BUTTON_STAT ", "NOTHIIG")

                        }



                    }
                }

                return v?.onTouchEvent(event) ?: true
            }
        }

        forwardButton.setOnTouchListener(onTouchListener)
        backwardButton.setOnTouchListener(onTouchListener)
        leftButton.setOnTouchListener(onTouchListener)
        rightButton.setOnTouchListener(onTouchListener)



        //deviceInfo.text = intent.getStringExtra("INFO")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connected)

        initialize()
    }

    override fun onStop() {
        super.onStop()

        Thread{
            m_os.write("Quit".toByteArray())
            m_os.flush()
            m_is.close()
            m_os.close()
            MainActivity.socket.close()

        }.start()

    }

    private fun writeInfo(name: String) {
        Thread{
            m_os.write(name.toByteArray())
            m_os.flush()

        }.start()
    }



}

