package asiantech.internship.summer.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-nhatnguyen`.activity_recycle_view.*
import kotlin.random.Random

class RecycleViewActivity : AppCompatActivity() {
    private lateinit var timeLineItems: MutableList<TimeLineItem?>
    private lateinit var timeLineAdapter: TimeLineAdapter
    var isLoading = false
    private var listItemTimeLine = mutableListOf(R.drawable.img_food_1,
            R.drawable.img_food_2,
            R.drawable.img_food_3,
            R.drawable.img_food_4,
            R.drawable.img_food_5,
            R.drawable.img_food_6,
            R.drawable.img_food_7,
            R.drawable.img_food_8,
            R.drawable.img_food_9,
            R.drawable.img_food_10,
            R.drawable.img_food_11,
            R.drawable.img_food_12)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_view)
        initData()
        initAdapter()
        initOnScrollListener()
        refresh()
    }

    private fun refresh() {
        swipeRefreshLayout.setOnRefreshListener {
            Handler().postDelayed({
                timeLineAdapter.clear()
                initData()
                initAdapter()
                swipeRefreshLayout.isRefreshing = false
            }, 1500)
        }
    }

    private fun initAdapter() {
        timeLineItems = timeLineItems.subList(0, 10)
        recyclerViewTimeLine.layoutManager = LinearLayoutManager(this)
        timeLineAdapter = TimeLineAdapter(timeLineItems)
        recyclerViewTimeLine.adapter = timeLineAdapter
    }

    private fun initData() {
        timeLineItems = mutableListOf()
        for (i in 0..14) {
            val index = Random.nextInt(listItemTimeLine.size)
            timeLineItems.add(TimeLineItem(listItemTimeLine[index], true, 0, "nickName ${Random.nextInt(10)}"))
        }
    }

    private fun initOnScrollListener() {
        recyclerViewTimeLine.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lineLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItem = lineLayoutManager.findLastVisibleItemPosition()
                if (!isLoading) {
                    if (lastVisibleItem == timeLineItems.size - 1) {
                        loadMore()
                        isLoading = true
                    }
                }
            }
        })
    }

    private fun loadMore() {
        timeLineItems.add(null)
        timeLineAdapter.notifyItemInserted(timeLineItems.size - 1)

        Handler().postDelayed({
            timeLineItems.removeAt(timeLineItems.size - 1)
            val scrollPosition = timeLineItems.size
            timeLineAdapter.notifyItemRemoved(scrollPosition)
            val nextLimit = scrollPosition + 10
            if (scrollPosition < nextLimit) {
                for (i in 0..9) {
                    timeLineItems.add(TimeLineItem(listItemTimeLine[Random.nextInt(listItemTimeLine.size)], true, 0, "nickName ${Random.nextInt(10)}"))
                }
            }
            timeLineAdapter.notifyDataSetChanged()
            isLoading = false
        }, 2500)
    }
}
