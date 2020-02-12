package com.himanshu.tasked.data.sessionManagement

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.himanshu.tasked.data.Result
import com.himanshu.tasked.data.mappers.LoggerInUserMapper
import com.himanshu.tasked.data.models.LoggedInUser
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class UserSessionManager constructor(private val firebaseAuth: FirebaseAuth) {


    /**
     * Logs Out
     */
    suspend fun logOut() {
        firebaseAuth.signOut()
    }

    suspend fun userLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

    suspend fun getLoggedInUser(): LoggedInUser? {

        return if (firebaseAuth.currentUser != null) {
            LoggerInUserMapper.toLoggerInUser(firebaseAuth.currentUser!!)
        } else
            null
    }

    /**
     * Creates A User With Email and Password
     */
    suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String
    ) = suspendCoroutine<LoggedInUser> { continuation ->
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                continuation.resume(LoggerInUserMapper.toLoggerInUser(it.user!!))
            }
            .addOnFailureListener {
                continuation.resumeWithException(it)
            }
    }

    /**
     * Login With Email Password
     */
    suspend fun loginWithEmailAndPassword(
        email: String,
        password: String
    ) = suspendCoroutine<LoggedInUser> { continuation ->

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                continuation.resume(LoggerInUserMapper.toLoggerInUser(it.user!!))
            }
            .addOnFailureListener {
                continuation.resumeWithException(it)
            }
    }


    suspend fun loginWithGoogle(googleSignInAccount: GoogleSignInAccount): Result<LoggedInUser> {
        // handle Google login
        return firebaseAuthWithGoogle(googleSignInAccount)
    }

    private suspend fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) =
        suspendCoroutine<Result<LoggedInUser>> { continuation ->

            val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
            firebaseAuth.signInWithCredential(credential)
                .addOnSuccessListener {

                    if (it.user == null) {
                        continuation.resumeWithException(IllegalStateException("firebaseAuthWithGoogle: got null user"))
                    } else {
                        val loggedInUser = LoggerInUserMapper.toLoggerInUser(it.user!!)
                        continuation.resume(Result.Success(loggedInUser))
                    }
                }
                .addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
}