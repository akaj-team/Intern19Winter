package asiantech.internship.summer.viewpagertablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R

class TabFragment : Fragment() {

    companion object {
        private val IMAGE_RESOURCE = "image_resource"

        fun newInstance(imgResouce: Int): TabFragment {
            val bundle = Bundle()
            bundle.putInt(IMAGE_RESOURCE, imgResouce)
            val tabFragment = TabFragment()
            tabFragment.arguments = bundle
            return tabFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tab, container, false)
        val imgTab = view.findViewById<ImageView>(R.id.imgTabLayout)
        arguments?.getInt(IMAGE_RESOURCE)?.let { imgTab.setImageResource(it) }
        return view
    }
}
