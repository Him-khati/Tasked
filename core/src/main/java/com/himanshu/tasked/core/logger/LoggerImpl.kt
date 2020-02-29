package com.himanshu.tasked.core.logger

import com.vinners.core.logger.Logger
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoggerImpl @Inject constructor() : Logger {

    fun init(debugMode: Boolean) {
        if (debugMode) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(TimberReleaseTree())
        }
    }

    override fun d(message: String, vararg args: Any) {
        Timber.d(message, args)
    }

    override fun d(tag: String, message: String, vararg args: Any) {
        Timber.d(tag, message, args)
    }

    override fun d(t: Throwable) {
        Timber.d(t)
    }

    override fun d(t: Throwable, message: String, vararg args: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun e(message: String, vararg args: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun e(t: Throwable) {
        Timber.e(t)
    }

    override fun e(t: Throwable, message: String, vararg args: Any) {
        Timber.e(t, message, args)
    }

    override fun eAndRethrow(t: Throwable, message: String, vararg args: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun i(message: String, vararg args: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun i(t: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun i(t: Throwable, message: String, vararg args: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setUserInfoForLogger(
        userIdentifier: String,
        userName: String,
        userEmail: String?
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unBindUserFromLogger() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun v(message: String, vararg args: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun v(t: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun v(t: Throwable, message: String, vararg args: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun w(message: String, vararg args: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun w(t: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun w(t: Throwable, message: String, vararg args: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun wtf(message: String, vararg args: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun wtf(t: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun wtf(t: Throwable, message: String, vararg args: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}