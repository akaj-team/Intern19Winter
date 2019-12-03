package asiantech.internship.summer.savedata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-tamle`.fragment_done.*

class DoneFragment : Fragment() {
    private var mActivity: SavedataActivity? = null
    private var mDataTodos: MutableList<DataTodo>? = null
    private var mTodo: Todo? = null
    private var mDoneAdapter: DoneAdapter? = null
    private var mIdUser = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_done, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity = activity as SavedataActivity

        initView()
    }

    private fun initView() {
        mTodo = Todo(mActivity!!)
        mIdUser = Preference(mActivity!!).getInt(Preference.ID_USER)
        if (mIdUser != 0) {
            mDataTodos = mTodo?.getlist(mIdUser, 1)

            recyclerViewDone.setHasFixedSize(true)
            val layoutManager = LinearLayoutManager(mActivity, RecyclerView.VERTICAL, false)
            recyclerViewDone.addItemDecoration(DividerItemDecoration(activity as SavedataActivity, layoutManager.orientation))
            recyclerViewDone.setLayoutManager(layoutManager)
            mDoneAdapter = DoneAdapter(mActivity!!, mDataTodos!!)
            recyclerViewDone.adapter = mDoneAdapter
        }
    }


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && isResumed) {
            mDataTodos?.clear()
            mDataTodos?.addAll(mTodo!!.getlist(mIdUser, 1))
            mDoneAdapter?.notifyDataSetChanged()
        }
    }
}
