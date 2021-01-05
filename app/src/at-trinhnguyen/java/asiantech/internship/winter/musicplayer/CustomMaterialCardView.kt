package asiantech.internship.winter.musicplayer

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView
import com.google.android.material.shape.CornerFamily


class CustomMaterialCardView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null
) : MaterialCardView(context, attrs) {

    fun setTopRadius(radius: Float) {
        shapeAppearanceModel = shapeAppearanceModel
                .toBuilder()
                .setTopLeftCorner(CornerFamily.ROUNDED, radius)
                .setTopRightCorner(CornerFamily.ROUNDED, radius)
                .setBottomRightCorner(CornerFamily.ROUNDED, 0f)
                .setBottomLeftCorner(CornerFamily.ROUNDED, 0f)
                .build()
        invalidate()
    }
}

