package com.example.whatsapp.models

data class Message(
    val senderPhoneNumber : String = "",
    val message: String = "",
    val timeStamp: Long = 0L
)
