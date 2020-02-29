package com.himanshu.tasked.data.di

import com.google.firebase.auth.FirebaseAuth
import com.himanshu.tasked.data.sessionManagement.UserSessionManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class DataModule {

    @Module
    companion object {

        @Singleton
        @Provides
        @JvmStatic
        fun provideUserSessionManager(firebaseAuth: FirebaseAuth): UserSessionManager {
            return UserSessionManager(firebaseAuth)
        }

        @Singleton
        @Provides
        @JvmStatic
        fun provideFirebaseAuth(): FirebaseAuth {
            return FirebaseAuth.getInstance()
        }
    }
}