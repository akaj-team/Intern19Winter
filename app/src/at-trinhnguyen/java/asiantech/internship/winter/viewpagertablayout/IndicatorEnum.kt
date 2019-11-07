package asiantech.internship.winter.viewpagertablayout

import asiantech.internship.summer.R

enum class IndicatorEnum(private val mLayoutResId: Int) {
    BLUE(R.layout.view_indicator_blue),
    GREEN(R.layout.view_indicator_green),
    RED(R.layout.view_indicator_red);

    fun getLayoutResId(): Int {
        return mLayoutResId
    }
}
