package e.ustoz.data.repository.state

interface StateRepository {

    fun isFirstTime(): Boolean

    fun isActivated(): Boolean
}