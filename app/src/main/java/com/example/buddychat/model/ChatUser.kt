package com.example.buddychat.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatUser(
    val firstName : String,
    val userName : String
):Parcelable
