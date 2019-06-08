package app.saltforest.architecturesample.frontend.di.component

import app.saltforest.architecturesample.MemoApp
import app.saltforest.architecturesample.frontend.di.module.AppBindingModule
import app.saltforest.samplearchitecture.infra.di.InfraModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        InfraModule::class,
        AppBindingModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<MemoApp> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<MemoApp>

}