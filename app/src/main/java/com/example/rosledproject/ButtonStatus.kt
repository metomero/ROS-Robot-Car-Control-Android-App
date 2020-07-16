package com.example.rosledproject

enum class ButtonStatus(var intArray: IntArray) {
    FORWARD(intArrayOf(1,0,0,0)),
    FORWARD_RIGHT(intArrayOf(1,0,1,0)),
    FORWARD_LEFT(intArrayOf(1,0,0,1)),
    BACKWARD(intArrayOf(0,1,0,0)),
    BACKWARD_RIGHT(intArrayOf(0,1,1,0)),
    BACKWARD_LEFT(intArrayOf(0,1,0,1)),
    RIGHT(intArrayOf(0,0,1,0)),
    LEFT(intArrayOf(0,0,0,1)),
    STOP(intArrayOf(0,0,0,0))
    
}