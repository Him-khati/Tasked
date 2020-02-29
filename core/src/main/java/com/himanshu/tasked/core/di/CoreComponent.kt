package com.himanshu.tasked.core.di

import android.app.Application
import com.himanshu.tasked.core.di.modules.ContextModule
import com.himanshu.tasked.core.di.modules.CoreModule
import com.himanshu.tasked.data.di.DataModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DataModule::class,
        CoreModule::class,
        ContextModule::class
    ]
)
interface CoreComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): CoreComponent
    }

    fun injectApplication(application: Application)
}