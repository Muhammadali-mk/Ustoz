package e.ustoz.uz.presentation.features.user_info

import android.os.Bundle
import androidx.activity.addCallback
import dagger.Lazy
import e.ustoz.data.model.user_info.UserInfoResponse
import e.ustoz.uz.R
import e.ustoz.uz.utils.app.onBackPressedDispatcher
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class UserInfoFragment @Inject constructor() :
    MvpAppCompatFragment(R.layout.fragment_feature_user_info), UserInfoView {
    @Inject
    internal lateinit var lazyPresenter: Lazy<UserInfoPresenter>
    private val presenter by moxyPresenter { lazyPresenter.get() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this) { presenter.backToMain() }
    }
    override fun onUserDataLoading() {

    }

    override fun onUserDataSuccess(userInfo: UserInfoResponse) {
    }

    override fun onErrorUserInfo(throwable: Throwable) {
    }
}