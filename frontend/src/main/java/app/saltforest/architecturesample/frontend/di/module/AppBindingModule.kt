package app.saltforest.architecturesample.frontend.di.module

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import app.saltforest.architecturesample.MemoApp
import app.saltforest.architecturesample.frontend.di.scope.ActivityScope
import app.saltforest.architecturesample.frontend.ui.MainActivity
import app.saltforest.architecturesample.frontend.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppBindingModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    abstract fun bindContext(app: MemoApp): Context

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainBindingModule::class])
    abstract fun contributeMainActivity(): MainActivity

}