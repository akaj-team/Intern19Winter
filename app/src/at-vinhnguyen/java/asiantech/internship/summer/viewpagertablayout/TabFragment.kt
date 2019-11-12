package asiantech.internship.summer.viewpagertablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R

class TabFragment(val imgResouce: Int) : Fragment() {

    companion object {
        fun newInstance(imgResouce: Int): TabFragment {
            return TabFragment(imgResouce)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tab, container, false)
        val imgTab = view.findViewById<ImageView>(R.id.imgTabLayout)
        imgTab.setImageResource(imgResouce)
        return view
    }
}
