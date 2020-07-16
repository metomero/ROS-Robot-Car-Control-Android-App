package com.example.rosledproject

enum class Movement(var str: String) {
    FORWARD("1,0,1,0,100,100"),
    FORWARD_RIGHT("1,0,1,0,100,50"),
    FORWARD_LEFT("1,0,1,0,50,100"),
    BACKWARD("0,1,0,1,100,100"),
    BACKWARD_RIGHT("0,1,0,1,50,100"),
    BACKWARD_LEFT("0,1,0,1,100,50"),
    RIGHT("1,0,0,1,100,100"),
    LEFT("0,1,1,0,100,100"),
    STOP("0,0,0,0,100,100")
}


