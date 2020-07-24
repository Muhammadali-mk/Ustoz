package e.ustoz.uz.presentation.features.user_info

import e.ustoz.data.model.user_info.UserInfoResponse
import moxy.MvpAppCompatFragment

class UserInfoFragment:MvpAppCompatFragment(),UserInfoView {
    override fun onUserDataLoading() {

    }

    override fun onUserDataSuccess(userInfo: UserInfoResponse) {
    }

    override fun onErrorUserInfo(throwable: Throwable) {
    }
}