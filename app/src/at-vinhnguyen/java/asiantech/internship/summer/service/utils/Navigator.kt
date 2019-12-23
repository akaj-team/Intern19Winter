package asiantech.internship.summer.service.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


class Navigator {
    companion object {
        @JvmStatic
        fun loadFragment(fragmentManager: FragmentManager, containerViewId: Int,
                         fragment: Fragment, addToBackStack: Boolean, rootTag: String?) {
            val transaction = fragmentManager.beginTransaction()
            var currentFragment = fragmentManager.findFragmentByTag(rootTag)
            if (currentFragment == null) {
                currentFragment = fragment
                transaction.add(containerViewId, currentFragment)
            }
            if (addToBackStack) {
                transaction.addToBackStack(rootTag)
            } else {
                showFragment(fragmentManager, fragment)
            }
            transaction.commit()
        }

        @JvmStatic
        private fun showFragment(fragmentManager: FragmentManager, fragment: Fragment) {
            val transaction = fragmentManager.beginTransaction()
            for (i in 0 until fragmentManager.fragments.size) {
                transaction.hide(fragmentManager.fragments[i])
            }
            transaction.show(fragment)
            transaction.commit()
        }

        @JvmStatic
        fun removeFragment(fragmentManager: FragmentManager, rootTag: String?) {
            fragmentManager.popBackStack(rootTag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            val fragment = fragmentManager.findFragmentByTag(rootTag)
            if (fragment != null) {
                showFragment(fragmentManager, fragment)
            }
        }
    }

}