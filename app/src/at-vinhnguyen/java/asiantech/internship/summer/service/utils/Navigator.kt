package asiantech.internship.summer.service.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import asiantech.internship.summer.R


class Navigator {
    companion object {
//        @JvmStatic
//        fun loadFragment(fragmentManager: FragmentManager, containerViewId: Int,
//                         fragment: Fragment, addToBackStack: Boolean, rootTag: String?) {
//            val transaction = fragmentManager.beginTransaction()
//            var currentFragment = fragmentManager.findFragmentByTag(fragment.javaClass.simpleName)
//            if (currentFragment == null) {
//                currentFragment = fragment
//                transaction.add(containerViewId, fragment, fragment.javaClass.simpleName)
//            }
//            if (addToBackStack) {
//                transaction.addToBackStack(rootTag)
//            } else {
//                showFragment(fragmentManager, currentFragment)
//            }
//            transaction.commit()
//        }

        @JvmStatic
        fun loadFragment(fragmentManager: FragmentManager, containerViewId: Int,
                         fragment: Fragment, addToBackStack: Boolean, rootTag: String?) {
            val transaction = fragmentManager.beginTransaction()
//            var currentFragment = fragmentManager.findFragmentByTag(fragment.javaClass.simpleName)
//            if (currentFragment == null) {
//                currentFragment = fragment
//                transaction.add(containerViewId, fragment, fragment.javaClass.simpleName)
//            }
            transaction.replace(R.id.frmContainer, fragment, fragment.javaClass.simpleName)
            if (addToBackStack) {
                transaction.addToBackStack(rootTag)
            } else {
//                showFragment(fragmentManager, currentFragment)
            }
            transaction.commit()
        }

        @JvmStatic
        private fun showFragment(fragmentManager: FragmentManager, fragment: Fragment?) {
            val transaction = fragmentManager.beginTransaction()
            for (i in fragmentManager.fragments.indices) {
                transaction.hide(fragmentManager.fragments[i])
            }
            transaction.show(fragment!!)
            transaction.commit()
        }

        @JvmStatic
        fun removeFragment(fragmentManager: FragmentManager, rootTag: String?) {
            fragmentManager.popBackStack(rootTag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            val fragment = fragmentManager.findFragmentByTag(rootTag)
            showFragment(fragmentManager, fragment)
        }
    }

}