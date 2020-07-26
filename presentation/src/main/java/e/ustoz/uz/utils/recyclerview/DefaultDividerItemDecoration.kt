package e.ustoz.uz.utils.recyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import e.ustoz.uz.R

class DefaultDividerItemDecoration : RecyclerView.ItemDecoration() {
    @Volatile
    private var divider: Drawable? = null

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left =
            parent.paddingLeft + parent.resources.getDimensionPixelSize(R.dimen.space_start)
        val right = parent.width - parent.paddingRight
        val divider = getDivider(parent.context)
        val height = parent.resources.getDimensionPixelSize(R.dimen.divider)
        val childCount = parent.childCount
        if (childCount > 1) {
            for (i in 0 until childCount) {
                val child = parent.getChildAt(i)
                val params = child.layoutParams as RecyclerView.LayoutParams
                val top = child.bottom + params.bottomMargin
                val bottom = top + height
                divider.setBounds(left, top, right, bottom)
                divider.draw(c)
            }
        }
    }

    @SuppressLint("ResourceType", "Recycle")
    private fun getDivider(context: Context): Drawable {
        return if (divider != null) checkNotNull(divider)
        else synchronized(this) {
            return if (divider != null) checkNotNull(divider)
            else {
                divider =
                    context.obtainStyledAttributes(intArrayOf(android.R.attr.listDivider))
                        .getDrawable(0)
                checkNotNull(divider)
            }
        }
    }
}