package asiantech.internship.summer.fragment

import androidx.fragment.app.Fragment

interface OnReplaceFragmentListener {
    fun onFragmentInteraction(fragment: Fragment, isAddToBackTack: Boolean)
}
