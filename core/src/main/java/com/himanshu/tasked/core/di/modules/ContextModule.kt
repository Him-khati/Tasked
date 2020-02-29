package com.himanshu.tasked.core.di.modules

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ContextModule {

    @Binds
    abstract fun bindContext(application: Application): Context
}