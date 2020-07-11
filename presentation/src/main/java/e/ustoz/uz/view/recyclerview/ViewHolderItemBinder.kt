package e.ustoz.uz.view.recyclerview

interface ViewHolderItemBinder<T> {

    fun onBind(element: T)

    fun onUnbind() { /* ignored */ }
}