package com.himanshu.tasked.data.models

import android.net.Uri

data class LoggedInUser(
    val sessionToken: String,
    val displayName: String,
    val email: String,
    val mobileNumber: String? = null,
    val profilePictureUrl : Uri? = null
)