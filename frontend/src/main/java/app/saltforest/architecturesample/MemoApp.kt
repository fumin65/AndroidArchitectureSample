package app.saltforest.architecturesample

import app.saltforest.architecturesample.frontend.di.component.DaggerApplicationComponent
import app.saltforest.samplearchitecture.infra.di.InfraModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class MemoApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder()
            .infraModule(InfraModule(this))
            .apply { seedInstance(this@MemoApp) }
            .build()
    }
}