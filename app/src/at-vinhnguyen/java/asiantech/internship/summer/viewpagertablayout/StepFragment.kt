package asiantech.internship.summer.viewpagertablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R

class StepFragment(var stepName: String) : Fragment() {
    companion object {
        fun newInstance(stepName: String): Fragment {
            return StepFragment(stepName)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_step, container, false)
        val textViewStep = view.findViewById<TextView>(R.id.tvStep)
        textViewStep.text = stepName
        return view
    }
}
