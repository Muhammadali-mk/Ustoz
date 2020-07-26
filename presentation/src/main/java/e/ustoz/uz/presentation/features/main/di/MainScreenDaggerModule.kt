package e.ustoz.uz.presentation.features.main.di

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import e.ustoz.uz.global.di.GlobalScope
import e.ustoz.uz.presentation.features.main.MainScreenFragment
import e.ustoz.uz.presentation.features.main.features.course.CoursesFragment
import e.ustoz.uz.presentation.features.main.features.daybook.DaybookFragment
import e.ustoz.uz.presentation.features.main.features.material.MaterialsFragment
import e.ustoz.uz.presentation.features.main.features.test.TestsFragment
import e.ustoz.uz.presentation.features.main.route.MainScreenRouteController
import e.ustoz.uz.support.dagger.fragment.FragmentKey

@Module(includes = [MainScreenDaggerModule.Binder::class])
object MainScreenDaggerModule {

    @JvmStatic
    @Provides
    @GlobalScope
    fun provideRouteController(): MainScreenRouteController = MainScreenRouteController()

    @Module
    interface Binder {

        @Binds
        @IntoMap
        @FragmentKey(CoursesFragment::class)
        fun bindCoursesFragment(fragment: CoursesFragment): Fragment

        @Binds
        @IntoMap
        @FragmentKey(MaterialsFragment::class)
        fun bindMaterialsFragment(fragment: MaterialsFragment): Fragment

        @Binds
        @IntoMap
        @FragmentKey(TestsFragment::class)
        fun bindTestsFragment(fragment: TestsFragment): Fragment

        @Binds
        @IntoMap
        @FragmentKey(DaybookFragment::class)
        fun bindDaybookFragment(fragment: DaybookFragment): Fragment

        @Binds
        @IntoMap
        @FragmentKey(MainScreenFragment::class)
        fun bindMainFragment(fragment: MainScreenFragment): Fragment
    }
}