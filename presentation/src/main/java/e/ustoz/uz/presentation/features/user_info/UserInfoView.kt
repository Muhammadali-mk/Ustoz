package e.ustoz.uz.presentation.features.user_info

import e.ustoz.data.model.user_info.UserInfoResponse
import moxy.MvpView

interface UserInfoView : MvpView {

    fun onUserDataLoading()

    fun onUserDataSuccess(userInfo: UserInfoResponse)

    fun onErrorUserInfo(throwable: Throwable)
}