package com.example.buddychat

import android.text.format.DateFormat

//extention function
 fun convertDate(milliseconds: Long): String {
    return DateFormat.format("dd/MM/yyyy hh:mm a", milliseconds).toString()
}