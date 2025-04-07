package com.example.whatsapp.introdution.chatbox

data class ChatListModel(
    val image: String,
    val name: String,
    val time: String,
    val message: String,
    val userId: String? = null,
    val phoneNumber: String? = null,
    val profileImage: String? = null
)
