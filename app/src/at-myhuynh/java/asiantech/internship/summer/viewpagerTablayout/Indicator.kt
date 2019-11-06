package asiantech.internship.summer.viewpagerTablayout

import asiantech.internship.summer.R

enum class Indicator(private val mLayoutResId: Int) {
    YELLOW(R.layout.view_one),
    BLUE(R.layout.view_two),
    GREEN(R.layout.view_three);

    fun getLayoutResId(): Int {
        return mLayoutResId
    }
}
