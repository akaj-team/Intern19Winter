package asiantech.internship.summer.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-myhuynh`.activity_timeline.*
import kotlin.random.Random

class TimelineActivity : AppCompatActivity() {

    private lateinit var timeLines: MutableList<TimelineItem>
    private lateinit var timelineAdapter: TimelineAdapter
    val linearLayoutManager = LinearLayoutManager(this)

    private val listImages = mutableListOf(R.drawable.img_food_1,
            R.drawable.img_food_2,
            R.drawable.img_food_3,
            R.drawable.img_food_4,
            R.drawable.img_food_5,
            R.drawable.img_food_6,
            R.drawable.img_food_7,
            R.drawable.img_food_8,
            R.drawable.img_food_9,
            R.drawable.img_food_10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline)

        rvTimeline.layoutManager = linearLayoutManager

        setUpRV()

        swipeContainer.setOnRefreshListener {
            Handler().postDelayed({
                timelineAdapter.reset()
                setUpRV()
                swipeContainer.isRefreshing = false
            }, 1000)
        }
    }

    private fun setUpRV() {
        initData()
        timelineAdapter = TimelineAdapter(timeLines)
        rvTimeline.adapter = timelineAdapter
        setOnClickListener(timelineAdapter)
        recyclerViewOnScroll(timelineAdapter)
    }

    private fun recyclerViewOnScroll(timelineAdapter: TimelineAdapter) {
        rvTimeline.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!timelineAdapter.isLoading()) {
                    Log.d("xxx", "last visible position: ${linearLayoutManager.findLastVisibleItemPosition()}, total count: ${linearLayoutManager.itemCount}")
                    if (linearLayoutManager.findLastVisibleItemPosition() >= linearLayoutManager.itemCount - 1) {
                        Log.d("xxx", "end")

                        recyclerView.post {
                            timelineAdapter.addFooter()
                        }
                        Handler().postDelayed({
                            timelineAdapter.removeFooter()
                            val lastTimeLine = linearLayoutManager.itemCount
                            timelineAdapter.loadMore(lastTimeLine)
                        }, 2000)
                    }
                }
            }
        })
    }

    private fun initData() {
        timeLines = mutableListOf()
        for (i in 0..25) {
            val index = Random.nextInt(listImages.size)
            timeLines.add(TimelineItem("Name ${i + 1}", listImages[index], "こんにちは世界 $i", i))
        }
//        timeLines.shuffle()
    }

    private fun setOnClickListener(adapter: TimelineAdapter) {
        adapter.setOnClickListener(object : FavoriteOnClickListener {
            override fun onItemOnClick(timelineItem: TimelineItem) {
                timelineItem.isLike = !timelineItem.isLike
                if (timelineItem.isLike) {
                    timelineItem.like = timelineItem.like + 1
                } else {
                    timelineItem.like = timelineItem.like - 1
                }
                adapter.notifyDataSetChanged()
            }
        })
    }
}
