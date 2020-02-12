package com.himanshu.tasked.data.mappers

import com.google.firebase.auth.FirebaseUser
import com.himanshu.tasked.data.models.LoggedInUser
import java.util.*

object LoggerInUserMapper {

    fun toLoggerInUser(user: FirebaseUser): LoggedInUser {
        return LoggedInUser(
            sessionToken = UUID.randomUUID().toString(),
            displayName = user.displayName ?: "N/A",
            email = user.email
                ?: throw IllegalArgumentException("LoggerInUserMapper: Null mail obj received"),
            mobileNumber = user.phoneNumber,
            profilePictureUrl = user.photoUrl
        )
    }

}