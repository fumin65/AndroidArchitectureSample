package app.saltforest.architecturesample.frontend.di.module

import androidx.lifecycle.ViewModel
import app.saltforest.architecturesample.frontend.di.ViewModelKey
import app.saltforest.architecturesample.frontend.di.scope.FragmentScope
import app.saltforest.architecturesample.frontend.ui.memo.MemoListFragment
import app.saltforest.architecturesample.frontend.ui.memo.MemoViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MainBindingModule {

    @Binds
    @IntoMap
    @ViewModelKey(MemoViewModel::class)
    abstract fun bindMemoViewModel(viewModel: MemoViewModel): ViewModel

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeMemoListFragment(): MemoListFragment

}