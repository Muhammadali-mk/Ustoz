package e.ustoz.uz.support.delegate.view

import android.view.Menu
import android.view.MenuItem
import androidx.annotation.*
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner

@Suppress("unused")
class ToolbarDelegate(
    target: LifecycleOwner
) : ViewDelegate<Toolbar>(null, target) {

    fun setTitle(@StringRes resourceId: Int) =
        view?.setTitle(resourceId)

    @Suppress("UsePropertyAccessSyntax")
    fun setTitle(title: CharSequence): Unit? =
        view?.setTitle(title)

    fun setSubtitle(@StringRes resourceId: Int) =
        view?.setSubtitle(resourceId)

    @Suppress("UsePropertyAccessSyntax")
    fun setSubtitle(title: CharSequence): Unit? =
        view?.setSubtitle(title)

    @Suppress("MemberVisibilityCanBePrivate")
    fun setNavigationIcon(@DrawableRes resourceId: Int) =
        view?.setNavigationIcon(resourceId)

    @Suppress("MemberVisibilityCanBePrivate")
    fun setNavigationIcon(@DrawableRes drawableResId: Int, @ColorRes colorResId: Int) = view?.let {
        it.setNavigationIcon(drawableResId)
        it.navigationIcon?.setTint(ContextCompat.getColor(it.context, colorResId))
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun setNavigationOnClickListener(onClick: () -> Unit) =
        view?.setNavigationOnClickListener { onClick() }

    fun setNavigation(@DrawableRes resourceId: Int, onClick: () -> Unit) {
        setNavigationIcon(resourceId)
        setNavigationOnClickListener(onClick)
    }

    fun setNavigation(
        @DrawableRes drawableResId: Int,
        @ColorRes colorResId: Int,
        onClick: () -> Unit
    ) {
        setNavigationIcon(drawableResId, colorResId)
        setNavigationOnClickListener(onClick)
    }

    fun inflateMenu(
        @MenuRes menuResId: Int,
        listener: Toolbar.OnMenuItemClickListener, menu: (Menu) -> Unit = {}
    ) = view?.let {
        it.inflateMenu(menuResId)
        it.setOnMenuItemClickListener(listener)
        menu.invoke(it.menu)
    }

    fun inflateMenu(
        @MenuRes menuResId: Int,
        listener: (MenuItem) -> Boolean, menu: (Menu) -> Unit = {}
    ) {
        val onMenuItemClickListener = Toolbar.OnMenuItemClickListener { listener.invoke(it) }
        inflateMenu(menuResId, onMenuItemClickListener, menu)
    }

    fun findMenuItemById(@IdRes menuId: Int): MenuItem? =
        view?.menu?.findItem(menuId)

    fun removeMenu() {
        view?.menu?.clear()
    }

    fun removeNavigation() =
        view?.let { it.navigationIcon = null; it.setNavigationOnClickListener(null) }

}