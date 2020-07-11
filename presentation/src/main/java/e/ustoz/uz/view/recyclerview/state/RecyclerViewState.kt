package e.ustoz.uz.view.recyclerview.state

import kotlin.properties.Delegates

abstract class RecyclerViewState {
    public var itemViewPosition: ItemViewPosition by Delegates.notNull(); private set
    private var internalCall: Boolean = false

    fun getViewStateType(position: ItemViewPosition): ViewStateType {
        itemViewPosition = position
        return configureTypeByPosition(position)
    }

    abstract fun configureTypeByPosition(position: ItemViewPosition): ViewStateType

    enum class ViewStateType {
        BOTH, CONTENT, FOOTER
    }

    enum class ItemViewPosition {
        CONTENT, FOOTER
    }
}