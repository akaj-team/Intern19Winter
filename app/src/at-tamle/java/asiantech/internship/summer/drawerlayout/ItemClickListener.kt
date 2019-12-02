package asiantech.internship.summer.drawerlayout

import android.view.View

interface ItemClickListener {
    fun onClick(view: View, position: Int, isLongClick: Boolean)
}
